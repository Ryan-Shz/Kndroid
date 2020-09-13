package com.ryan.github.kndroid.practice.generic

/**
 * created by 2020/9/13 11:20 PM
 *
 * @author Ryan
 */
class GenericPractice<T> where T : Person {

    fun fill(array: Array<in T>, t: T) {
        array[0] = t
    }

    fun copy(from: Array<out T>, to: Array<in T>) {
        if (from.size > to.size) {
            return
        }
        for (i in from.indices) {
            to[i] = from[i]
        }
    }

    // 使用inline + reified使泛型类型可见
    inline fun <reified T> printIfTypeMatch(item: Any) {
        if (item is T) { // IDE 会提示错误，Cannot check for instance of erased type: T
            println(item)
        }
    }
}