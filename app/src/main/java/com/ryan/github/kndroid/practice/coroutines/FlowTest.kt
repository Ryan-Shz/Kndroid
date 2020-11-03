package com.ryan.github.kndroid.practice.coroutines

import com.ryan.github.kndroid.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.take

suspend fun main() {
    val intFlow = flow {
        emit(1)
        delay(1000)
        emit(2)
        emit(3)
        emit(4)
    }.flowOn(Dispatchers.IO)

    intFlow.collect {
        log(it)
    }
}