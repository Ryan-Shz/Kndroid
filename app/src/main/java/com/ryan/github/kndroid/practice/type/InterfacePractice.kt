package com.ryan.github.kndroid.practice.type

interface SimpleInterface {
    val simpleProperty: Int
}

class SimpleClass : SimpleInterface {
    override val simpleProperty: Int
        get() = 2

    val a = 1
}

fun test() {
    val ref = SimpleClass::a
}