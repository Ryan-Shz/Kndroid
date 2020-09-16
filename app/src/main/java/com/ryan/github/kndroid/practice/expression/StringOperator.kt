package com.ryan.github.kndroid.practice.expression

fun testStringOperator() {
    val str1 = "HelloWorld"
    val str2 = "World"
    println(str1 - str2)
    println(str1 * 3)
    println(str1 / "l")
}

// 实现字符串的减法
operator fun String.minus(other: Any?): String {
    return this.replaceFirst(other.toString(), "")
}

// 实现字符串的乘法
operator fun String.times(other: Int): String {
    return (1..other).joinToString("") {
        this
    }
}

// 实现字符串的除法
operator fun String.div(other: Any?): Int {
    val other = other.toString()
    return this.windowed(other.length, step = 1) {
        other == it
    }.count { it }
}