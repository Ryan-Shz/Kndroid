package com.ryan.github.kndroid.practice.type

import android.util.Log
import com.ryan.github.kndroid.TAG

fun buildFood() {
    val food1 = Food("Ryan", 12)
    val food2 = Food(name = "Ryan")
    val food3 = Food("Ryan")

    val typeRef = Food::type
    typeRef.get(food1)
}

// open表示类可以被继承
open class Food(val name: String, private val price: Int = 100, type: Int = 1) {

    // 类型
    val type = type

    // 副构造器，必须调用this(...)通过主构造器构造
    constructor(name: String) : this(name, 12) {
        Log.i(TAG, "constructor block")
    }

    // 副构造器
    constructor(age: Int) : this("Food", age)

    override fun toString(): String {
        return "name: $name, price: $price, type: $type"
    }

}