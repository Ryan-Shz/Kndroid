package com.ryan.github.kndroid.practice.type

import com.ryan.github.kndroid.isEmail
import java.io.File

fun main() {
    val s = "Hello"
    s.isEmail()
    println(s[0])

    val person = Person1()
    val name = person.name
    val name1 = person.name

    val len = name?.length
}

var PoorGuy.balance: Int
    get() = 12
    set(value) {age = value}

class PoorGuy(val name: String) {
    var age: Int = 1
        get() = field
}

interface SimpleInterface1 {
    var simpleProperty: Int
}

class Sample1: SimpleInterface1 {
    override var simpleProperty: Int = 1
        get() = field
        set(value) {}
}

