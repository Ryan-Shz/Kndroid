package com.ryan.github.kndroid.practice.function

class LambdaPractice {
    // 创建一个匿名函数
    val anonymousFunc1: () -> Unit = fun() {

    }
    // 创建一个Lambda形式的匿名函数
    val anonymousFunc2 = { name: String ->
        println(name)
    }
}