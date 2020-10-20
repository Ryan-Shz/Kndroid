package com.ryan.github.kndroid.practice.type

fun main() {
    val s: String? = "123"
    val len1 = s?.length
    println(len1)
    if (s != null) {
        val len2 = s?.length
        val len3 = s.length
        println(len2)
        println(len3)
    }

}

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
