package com.ryan.github.kndroid.practice.coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select
import java.io.File

// Deferred的select使用场景
suspend fun main() {
    GlobalScope.launch(Dispatchers.IO) {
        val localDeferred = getFromLocal()
        val netDeferred = getFromNetwork()
        val response = select<SelectResponse> {
            localDeferred.onAwait { SelectResponse(it, true) }
            netDeferred.onAwait { SelectResponse(it, false) }
        }
        println("response: ${response.isLocal}")
    }.join()
}

data class SelectResponse(val res: String, val isLocal: Boolean)

private fun CoroutineScope.getFromLocal(): Deferred<String> = async(Dispatchers.IO) {
    delay(1000)
    File("build.gradle").readText()
}

private fun CoroutineScope.getFromNetwork(): Deferred<String> = async(Dispatchers.IO) {
    delay(900)
    File("build.gradle").readText()
}