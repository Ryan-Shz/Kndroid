package com.ryan.github.kndroid.practice.type

fun testRange() {
    val intRange = 1..10
    intRange is IntRange
    val intRange2 = IntRange(1, 10)

}