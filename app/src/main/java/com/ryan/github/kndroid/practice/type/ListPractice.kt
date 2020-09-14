package com.ryan.github.kndroid.practice.type

import android.util.Log
import com.ryan.github.kndroid.TAG

fun avgNumbersByIntArray() {
    // use array
    val intArray = IntArray(100000) {
        it + 1
    }
    val sum = intArray.sum()
    val avg = sum / intArray.size
    Log.i(TAG, "$avg")
}

fun avgNumbersByArray() {
    val range: IntRange = 1..100000
    val arr = range.toList().toIntArray()
    val intArray = intArrayOf(*arr)
    val ave = intArray.sum() / intArray.size
    Log.i(TAG, "$ave")
}

fun avgNumbersByList() {
    val range = 1..100000
    val list = range.toList()
    val avg = list.sum() / list.size
    Log.i(TAG, "$avg")
}

fun divByTree(): List<Int> {
    val list = listOf(21, 40, 11, 33, 78)
    return list.filter {
        it % 3 == 0
    }
}