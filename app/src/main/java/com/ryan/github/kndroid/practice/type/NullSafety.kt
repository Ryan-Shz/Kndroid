package com.ryan.github.kndroid.practice.type

class Sample {
    var name: String? = "Hello"

    fun printName() {
        // Right!
        name = null

        // 强转为不可空类型，不安全，可能空指针，不建议使用
        val length1 = name!!.length

        // 使用这种方式会导致length也可能为null
        val length2 = name?.length

        // 安全访问, ?:表示为null时的取值
        val length3 = name?.length ?: 0
    }
}