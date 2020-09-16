package com.ryan.github.kndroid.practice.function

class LambdaPractice {
    val anonymousFunc1: () -> Unit = fun() {

    }

    val anonymousFunc2 = { name: String ->
        println(name)
    }
}