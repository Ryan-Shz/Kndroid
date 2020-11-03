package com.ryan.github.kndroid.practice.coroutines

import com.ryan.github.kndroid.log

fun main() {
    val fibonacci = sequence {
        yield(1L)
        yield(2L)
        yield(3L)
        yield(4L)
        yield(5L)
        yield(6L)
    }
    fibonacci.take(9).forEach(::log)
}