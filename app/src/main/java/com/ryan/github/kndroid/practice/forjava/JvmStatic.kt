package com.ryan.github.kndroid.practice.forjava

class JvmStaticTest {

    companion object {
        @JvmStatic
        fun say() {
            println("Hello World!")
        }
    }
}