package com.ryan.github.kndroid

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlin.properties.Delegates

fun main() {
    val test = Test("Ryan", 11, 1)
    val (a, b, c) = test

    test.say()

    val test1 = Test1()
    println(test1.name)
}

data class Test(val name: String, val age: Int, val sex: Int)

fun Test.say() {
    println("hello")
}

class Test1 {
    var name by Delegates.notNull<String>()
}

class TestActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val btn = Button(this)
        var count = 0
        btn.setOnClickListener {
            count++
        }
    }
}