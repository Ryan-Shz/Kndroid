package com.ryan.github.kndroid.practice.function

import android.util.Log
import com.ryan.github.kndroid.TAG

inline fun printlnTimeCost(tag: String, block: () -> Unit) {
    Log.i(TAG, "----------start----------")
    val start = System.currentTimeMillis()
    block()
    Log.i(TAG, "$tag cost time: ${System.currentTimeMillis() - start}ms")
    Log.i(TAG, "----------end----------")
}