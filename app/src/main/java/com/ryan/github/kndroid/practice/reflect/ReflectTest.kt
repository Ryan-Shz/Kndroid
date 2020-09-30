package com.ryan.github.kndroid.practice.reflect

import java.lang.reflect.ParameterizedType
import kotlin.reflect.full.declaredFunctions

fun main() {
    getGenericReturnType()
    getSuperGenericType()
}

// 获取方法返回值的泛型实参
fun getGenericReturnType() {
    val clz = Api::class
    val func = clz.declaredFunctions.first { it.name == "getUsers" }
    func.returnType
        .arguments
        .forEach {
            println(it)
        }
    // 更简单的方式
    Api::getUsers.returnType.arguments.forEach {
        println(it)
    }
    // 使用java反射
    Api::class.java.getDeclaredMethod("getUsers")
        .genericReturnType
        .safeAs<ParameterizedType>()
        .actualTypeArguments.forEach {
            println(it)
        }
}

// 获取父类的泛型实参
fun getSuperGenericType() {
    val type = SubType::class.supertypes.first()
    type.arguments.forEach {
        println(it)
    }
    // 使用Java反射
    SubType::class.java.genericSuperclass?.safeAs<ParameterizedType>()?.actualTypeArguments?.forEach {
        println(it)
    }
}

fun <T> Any.safeAs(): T {
    return this as T
}

class UserDTO

interface Api {
    fun getUsers(): List<UserDTO>
}

abstract class SuperType<T> {
    // 在父类中获取泛型实参
    val typeParameter by lazy {
        // 因为是抽象类，在运行时，this是它的实际子类
        this::class.supertypes.first().arguments.first().type!!
    }
    val typeParameterByJava by lazy {
        this::class.java.genericSuperclass?.safeAs<ParameterizedType>()
            ?.actualTypeArguments?.first()
    }
}

class SubType : SuperType<String>()