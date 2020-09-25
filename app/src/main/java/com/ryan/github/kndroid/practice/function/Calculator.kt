package com.ryan.github.kndroid.practice.function

import java.lang.Exception

fun main(vararg args: String) {
    calculator(*args)
}

// 实现一个简单的4则运算计算器
// Simple Calculator:
// Input: 3 * 4
// Output: 12
fun calculator(vararg args: String) {
    if (args.size < 3) {
        return showHelp()
    }
    try {
        val a = args[0].toInt()
        val b = args[2].toInt()
        val operator = args[1]
        val operatorMap = mapOf(
            "+" to ::plus,
            "-" to ::minus,
            "*" to ::times,
            "/" to ::div
        )
        val func = operatorMap[operator] ?: return showHelp()
        var result = func(a, b)
        println("Input: ${args.joinToString(" ")}")
        println("Output: $result")
    } catch (e: Exception) {
        showHelp()
    }
}

private fun showHelp() {
    return println(
        """
        Invalid Arguments.
        ------------------
        Simple Calculator:
            Input: 3 * 4
            Output: 12
    """.trimIndent()
    )
}

private fun plus(a: Int, b: Int): Int {
    return a + b;
}

private fun minus(a: Int, b: Int): Int {
    return a - b
}

private fun times(a: Int, b: Int): Int {
    return a * b
}

private fun div(a: Int, b: Int): Int {
    return a / b
}