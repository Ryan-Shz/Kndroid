package com.ryan.github.kndroid.practice.coroutines.lite

import java.util.concurrent.Executors
import kotlin.coroutines.*

interface Dispatcher {
    fun dispatch(block: () -> Unit)
}

private object CommonPoolDispatcher : Dispatcher {

    private val executor =
        Executors.newFixedThreadPool(2 * Runtime.getRuntime().availableProcessors()) {
            Thread(it, "CommonPool").apply { isDaemon = true }
        }

    override fun dispatch(block: () -> Unit) {
        executor.submit(block)
    }
}

object CommonPool : DispatcherContext(CommonPoolDispatcher)

open class DispatcherContext(private val dispatcher: Dispatcher) :
    AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor {

    override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> =
        DispatcherContinuation(
            continuation.context.fold(continuation, { continuation, element ->
                if (element != this && element is ContinuationInterceptor) {
                    element.interceptContinuation(continuation)
                } else {
                    continuation
                }
            }), dispatcher
        )
}

private class DispatcherContinuation<T>(val delegate: Continuation<T>, val dispatcher: Dispatcher) :
    Continuation<T> {
    override val context: CoroutineContext
        get() = delegate.context

    override fun resumeWith(result: Result<T>) {
        dispatcher.dispatch {
            delegate.resumeWith(result)
        }
    }
}