package com.ryan.github.kndroid.practice.coroutines.lite

import com.ryan.github.kndroid.log

fun main() {
    log(1)
    val job = launch {
        log(-1)
        log("hello word")
        log(-2)
    }
    log(3)
    readLine()
}