package com.ryan.github.kndroid.practice.function

fun testFuncType() {
    // 无Receiver的函数类型
    val func1: () -> Unit = ::func1
    val func2: (String) -> Int = ::func2
    // 有Receiver的函数类型
    val func3: (A, String, Int) -> String = A::func3
    val func4: A.(String, Int) -> String = A::func3
    val func5 = A::func3
}

fun func1() {
    println("Hello")
}

fun func2(arg: String): Int {
    return 0
}

class A {
    fun func3(name: String, age: Int): String {
        return ""
    }
}
