package com.ryan.github.kndroid.practice.function

import java.lang.Exception

// 实现一个简单的4则运算计算器
// Simple Calculator:
// Input: 3 * 4
// Output: 12
fun calculator(vararg args: String) {
    if (args.size < 3) {
        return showHelp()
    }
    val a = args[0].toInt()
    val b = args[2].toInt()
    val operatorMap = mapOf(
        "+" to ::plus,
        "-" to ::minus,
        "*" to ::times,
        "/" to ::div
    )
    val operator = args[1]
    val func = operatorMap[operator] ?: return showHelp()
    var result = func(a, b)
    try {
        println("Input: ${args.joinToString(" ")}")
        println("Output: $result")
    } catch (e: Exception) {
        println("Invalid Arguments.")
        showHelp()
    }
}

private fun showHelp() {
    return print(
        """
        Simple Calculator:
        Input: 3 * 4
        Output: 12
    """.trimIndent()
    );
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