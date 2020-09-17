package com.ryan.github.kndroid.practice.type

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun arrayOp() {
    val arr1 = shortArrayOf(1, 2, 3)
    val arr2 = intArrayOf(1, 2, 3)
    val arr3 = longArrayOf(1L, 2L, 3L)
    val arr4 = floatArrayOf(1f, 2f, 3f)
    val arr5 = doubleArrayOf(1.0, 2.0, 3.0)
    val arr6 = charArrayOf('a', 'b', 'c')
    val arr7 = byteArrayOf(1, 2, 3)

    val arr8 = arrayOf(1, 2, 3)
    val arr9 = arrayOf('a', 'b', 'c')
    val arr10 = arrayOf(1.0, 2.0, 3.0)

    val arr11 = arrayOf("1", "2", "3")
    val arr12 = arrayOfNulls<DataClassPerson>(10)
}

fun rangeOp() {
    val range1 = 1..10
    val range2 = 1.0..10.0
}

fun listOp() {
    // 创建一个不可变的List
    val intList1 = listOf(1, 2, 3, 4)

    // 创建一个可变的List
    val intList2 = mutableListOf(1, 2, 3, 4)

    // 创建一个ArrayList，ArrayList是可变的
    val intList3 = ArrayList<Int>()

    // 创建一个LinkedList，LinkedList是可变的
    val intList4 = LinkedList<Int>()


    intList1.forEach {
        println(it)
    }

    for (element in intList1) {
        println(element)
    }

    for (index in intList1.indices) {
        println(intList1[index])
    }
}

fun mapOp() {
    // 创建一个不可变的Map
    val map1 = mapOf("name" to "Ryan", "age" to "20")
    // 创建一个可变的Map
    val map2 = mutableMapOf("name" to "Ryan", "age" to "20")
    // 创建一个HashMap
    val map3 = HashMap<String, String>()

    // 添加
    map2 += "name" to "Tom"
    map2.put("name", "Tom")

    // 删除
    map2.remove("name")
    map2 -= "name"

    2.to(3)

    // 修改
    map2["name"] = "Tom"

    // 查询
    println("name is: ${map2["name"]}")

    // 遍历
    // 1. 使用forEach遍历
    map1.forEach {
        println("key: ${it.key}, value: ${it.value}")
    }
    // 2. 使用for in遍历
    for (key in map1.keys) {
        println("key: $key, value: ${map1[key]}")
    }

    // 长度
    println("map size: ${map1.size}")

    // 包含
    map1.containsKey("name")
    map1.containsValue("Ryan")
}