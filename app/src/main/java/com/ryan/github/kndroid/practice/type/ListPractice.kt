package com.ryan.github.kndroid.practice.type

import com.ryan.github.kndroid.log

fun main() {
    printTimeCost("IntArray", ::avgNumbersByIntArray)
    printTimeCost("Array<Int>", ::avgNumbersByArray)
    printTimeCost("List<Int>", ::avgNumbersByList)

    val divByTreeList = divByTree()
    log("div by tree list: $divByTreeList")
}

fun avgNumbersByIntArray() {
    // use array
    val intArray = IntArray(100000) {
        it + 1
    }
    log("IntArray avg: ${intArray.average()}")
}

fun avgNumbersByArray() {
    val intArray = Array(100000) {
        it + 1
    }
    val avg = intArray.average()
    log("Array<Int> avg: $avg")
}

fun avgNumbersByList() {
    val range = 1..100000
    val list = range.toList()
    log("List<Int> avg: ${list.average()}")
}

fun divByTree(): List<Int> {
    val list = listOf(21, 40, 11, 33, 78)
    return list.filter {
        it % 3 == 0
    }
}

fun printTimeCost(tag: String, block: () -> Unit) {
    val start = System.currentTimeMillis()
    block()
    log("$tag cost time: ${System.currentTimeMillis() - start}ms")
    log("--------------------")
}