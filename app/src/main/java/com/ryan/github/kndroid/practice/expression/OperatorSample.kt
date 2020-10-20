package com.ryan.github.kndroid.practice.expression

fun main() {
    "Hello" == "World"
    "Hello".equals("World")

    2 + 3
    2.plus(3)

    val list = listOf(1, 2, 3, 4)

    2 in list
    list.contains(2)

    val map = mutableMapOf("Hello" to 2, "World" to 3)
    val value = map["Hello"]
    map["World"] = 4

    val func = fun() {
        println("Hello")
    }
    func()
    func.invoke()

    2 > 3
    2.compareTo(3) > 0

    2 to 3
    val book = Book()
    val desk = Desk()

    book on desk

    val s1 = "HelloWorld"
    val s2 = "World"
    s1 + s2
    println("minus result: ${s1 - s2}")
}

class Book
class Desk

infix fun Book.on(desk: Desk){}