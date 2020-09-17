package com.ryan.github.kndroid.practice.type

fun SequenceTest() {
    // 直接创建
    val sequence1 = sequenceOf(1, 2, 3)
    // 使用Iterable创建
    val list = listOf(1, 2, 3)
    val sequence2 = list.asSequence()
    // 使用lambda表达式创建
    val sequence3 = generateSequence(0) { it + 1 }

    val result1 = sequence1
        .map { i ->
            println("Map $i")
            i * 2
        }.filter {i ->
            println("Filter $i")
            i % 3  == 0
        }

    val result2 = list
        .map { i ->
            println("Map $i")
            i * 2
        }
        .filter { i ->
            println("Filter $i")
            i % 3  == 0
        }
}