package com.ryan.github.kndroid.practice.expression

fun whenEx(): String {
    val x = "1234"
    return when {
        x is String -> x
        x == "Ryan" -> "234"
        true -> "123"
        else -> "default"
    }
}