package com.kotlin.lesson.practice.lesson7

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.concurrent.Callable

fun main() {
    val context: Context? = null
    // Kotlin中的里氏替换
    val textView: TextView = Button(context)

    val testTv = TextView(context)
    val testBtn = Button(context)

    // kotlin的泛型不变性
    var parent1 = Generic1<TextView>(testTv)
    val child1 = Generic1<Button>(testBtn)
    // parent1 = child1 // Error!

    // 等价于Java的extends
    var parent2: Generic2<TextView> = Generic2(testTv)
    val child2: Generic2<Button> = Generic2(testBtn)
    val c: Generic2<String> = Generic2("23")
    // 协变点
    parent2 = child2

    // 等价于Java的super
    var parent3: Generic3<Button> = Generic3(testTv)
    val child3: Generic3<TextView> = Generic3(testBtn)
    // 逆变点
    parent3 = child3

    // 等价于Java的 ?
    var parent4: Generic1<*> = Generic1(testTv)
    val child4: Generic1<Button> = Generic1(testBtn)
    parent4 = child4

    // kotlin中的list是协变的！
    var textViews: List<TextView> = ArrayList()
    val buttons: List<Button> = ArrayList()
    textViews = buttons

    // 真泛型？
    printIfTypeMatch<String>(1)
}

class Generic1<T>(val t: T)

// out限制：
// 1. 只能向外提供泛型T对象
// 2. 不能修改泛型对象
class Generic2<out T>(val t: T) {
    private fun test(t: T): T {
        return t
    }
}

// in限制：
// 1. 不能向外提供泛型对象！
// 2. 可以修改泛型对象
class Generic3<in T>(private var t: T) {
    private fun test(t: T): T {
        return t
    }
}

fun <T> test(t: T): T {
    return t
}

// where 声明多个边界
class Generic4<out T>(t: T) where T : Runnable, T : View.OnClickListener

inline fun <reified T> printIfTypeMatch(item: Any) {
    if (item is T) { // Right!
        println(item)
    }
}