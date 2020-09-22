package com.ryan.github.kndroid.practice.function

import java.util.concurrent.Executors

fun testSam() {
    val executors = Executors.newCachedThreadPool()
    // Sam接口可以直接转换为Lambda表达式
    executors.submit(object : Runnable {
        override fun run() {
            println("Hello Sam")
        }
    })
    // 可以不写object关键字
    executors.submit(Runnable {
        println("Hello Sam")
    })
    // 可以不写Runnable
    executors.submit {
        println("Hello Sam")
    }

    // 多个参数时，不能去掉Runnable
    testSam1("1", Runnable { TODO("Not yet implemented") })

    testSam2(object : Kotlin {
        override fun study() {

        }
    })
}

fun testSam1(name: String, runnable: Runnable) {

}

fun testSam2(kotlin: Kotlin) {

}

interface Kotlin {
    fun study()
}