package com.ryan.github.kndroid.practice.reflect

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

data class Person(val id: Int, val name: String, val group: Group)

data class Group(val id: Int, val name: String, val location: String)

fun main() {
    // 浅复制
    val person = Person(1, "Ryan", Group(1, "Shz", "China"))
    val copy = person.copy()
    println(person === copy)
    println(person.group === copy.group)

    // 深复制
    val deepCopy = person.deepCopy()
    println(person === deepCopy)
    println(person.group === deepCopy.group)
}

// 实现深复制的顶级扩展函数
fun <T : Any> T.deepCopy(): T {
    if (this::class.isData) {
        return this
    }
    return this::class.primaryConstructor!!.let { primaryConstructor ->
        primaryConstructor.parameters.map { parameter ->
            // 获取主构造器中的当前属性的值
            val value = this::class.safeAs<KClass<T>>()
                .memberProperties
                .first { it.name == parameter.name }
                .get(this)
            if ((parameter.type.classifier as? KClass<*>)?.isData == true) {
                // 如果当前参数是Data Class类型，则表示需要深拷贝
                parameter to value?.deepCopy()
            } else {
                // 当前参数不是Data Class，直接创建pair
                parameter to value
            }
        }.toMap().let(primaryConstructor::callBy)
    }
}