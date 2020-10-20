package com.ryan.github.kndroid.practice.coroutines

import com.ryan.github.kndroid.log
import kotlinx.coroutines.*
import java.lang.IllegalStateException
import kotlin.concurrent.thread

fun main() =
//    start()
    join()
//    cancel()
//    async()
//    threadSafe()
//    completableDeferred()

// 启动协程
fun start() {
    log(1)
    GlobalScope.launch {
        log(-1)
        delay(1000)
        log(-2)
    }
    log(2)
    Thread.sleep(5000)
    log(3)
}

// 协程join的使用
fun join() = runBlocking {
    log(1)
    val job = GlobalScope.launch {
        log(-1)
        delay(1000)
        log(-2)
    }
    log(2)
    // 等待协程执行完成
    job.join()
    log(3)
}

// 取消协程
fun cancel() = runBlocking {
    log(-1)
    val job = launch {
        log(1)
        delay(2000)
        log(2)
    }
    log(-2)
    // 取消协程
    job.cancel()
    job.join()
    log(-3)
}

// async的使用
// 和launch的区别：async返回结果，launch不返回结果
fun async() = runBlocking {
    log(-1)
    val result = async {
        log(1)
        delay(1000)
        "HelloWorld"
    }
    log(-2)
    log(result.await())
    log(-3)
}

// 线程安全的例子
var num = 0

fun threadSafe() = runBlocking {
    List(1000) {
        launch {
            repeat(1000) {
                num++
            }
        }
    }.forEach {
        it.join()
    }
    log(num)
}

// CompletableDeferred的使用
// 将回调改造为协程同步调用，而不需要修改loadAsync函数中的实现
fun completableDeferred() = runBlocking {
    log(-1)
    launch {
        log(1)
        try {
            val result = load()
            log(result)
        } catch (e: Throwable) {
            log(e)
        }
    }
    log(-2)
}

suspend fun load(): String {
    val completeDeferred = CompletableDeferred<String>()
    loadAsync(object : TestCallback {
        override fun onSuccess(result: String) {
            completeDeferred.complete(result)
        }

        override fun onError(e: Throwable) {
            completeDeferred.completeExceptionally(e)
        }
    })
    return completeDeferred.await()
}

interface TestCallback {
    fun onSuccess(result: String)
    fun onError(e: Throwable)
}

fun loadAsync(callback: TestCallback) {
    thread {
        try {
            Thread.sleep(2000)
            if (Math.random() > 0.5f) {
                callback.onSuccess("Hello World")
            } else {
                throw IllegalStateException("This is a Demonstration Error.")
            }
        } catch (e: Throwable) {
            callback.onError(e)
        }
    }
}