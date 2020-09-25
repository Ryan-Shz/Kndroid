package com.ryan.github.kndroid.practice.coroutines.lite

import java.lang.IllegalStateException
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.*

typealias OnComplete<T> = (T?, Throwable?) -> Unit

// 用来定义协程的状态
sealed class State {
    // 未完成状态
    object InComplete : State()

    // 已完成状态，创建此状态时，需要把执行结果作为构造参数传入
    class Complete<T>(val value: T? = null, val exception: Throwable? = null) : State()

    // 未完成状态，用来保存协程执行完成后的回调
    class CompleteHandler<T>(val handler: OnComplete<T>) : State()
}

abstract class AbstractCoroutine<T>(
    // 覆盖Continuation接口定义的context对象
    override val context: CoroutineContext,
    block: suspend () -> T
) : Continuation<T> {

    // 用来保存协程状态
    private val state = AtomicReference<State>()

    init {
        // 状态初始化为未完成
        state.set(State.InComplete)
        // 启动协程，将自身传入，block执行完成后会回调resumeWith方法
        block.startCoroutine(this)
    }

    // 判断是否是完成状态
    val isCompleted
        get() = state.get() is State.Complete<*>

    // block执行完成后会调用这个方法
    override fun resumeWith(result: Result<T>) {
        try {
            if (result.isSuccess) {
                resume(result.getOrThrow())
            } else {
                resumeWithException(result.exceptionOrNull())
            }
        } catch (e: Throwable) {
            resumeWithException(e)
        }
    }

    private fun resume(value: T) {
        // 将当前状态设置为完成状态，并返回之前的状态
        val currentState = state.getAndSet(State.Complete(value))
        // 如果之前的状态时CompleteHandler，当前完成了，则需要回调
        when (currentState) {
            is State.CompleteHandler<*> -> {
                (currentState as State.CompleteHandler<T>).handler(value, null)
            }
        }
    }

    private fun resumeWithException(exception: Throwable?) {
        // 和resume的逻辑一样
        when (val currentState = state.getAndSet(State.Complete(null, exception))) {
            is State.CompleteHandler<*> -> {
                currentState.handler(null, exception)
            }
        }
    }

    suspend fun join() {
        when (state.get()) {
            is State.InComplete -> return joinSuspend()
            is State.Complete<*> -> return
        }
    }

    private suspend fun joinSuspend() = suspendCoroutine<Unit> { continuation ->
        doOnCompleted { _, _ ->
            continuation.resume(Unit)
        }
    }

    protected fun doOnCompleted(block: OnComplete<T>) {
        if (!state.compareAndSet(State.InComplete, State.CompleteHandler<T>(block))) {
            when (val currentState = state.get()) {
                is State.Complete<*> -> {
                    (currentState as State.Complete<T>).let {
                        block(currentState.value, currentState.exception)
                    }
                }
                else -> throw IllegalStateException("Invalid State: $currentState")
            }
        }
    }
}