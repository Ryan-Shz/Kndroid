// 1. 表示这个文件生成一个类，类名叫JvmNameTest
@file:JvmName("JvmNameTest")

package com.ryan.github.kndroid.practice.forjava

// 2. 作用在getter、setter上，修改它们的名称
@get:JvmName(name = "getTestName")
val name: String = "Ryan"

@get:JvmName(name = "getTestAge")
@set:JvmName(name = "setTestAge")
var age: Int = 1

// 3. 作用在函数上，修改函数的名称
@JvmName(name = "sayTest")
fun say() {
    println("Hello World")
}

