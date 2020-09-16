package com.ryan.github.kndroid.practice.function

import kotlin.concurrent.thread

/**
 * created by 2020/9/14 12:54 AM
 *
 * @author Ryan
 */
class Foo {
    fun bar() = println("bar")
}

fun test() {
    val foo = Foo()
    val bar: (Foo) -> Unit = Foo::bar

    foo.bar()
    bar.invoke(foo)

    multiParameters(1, 2, 3, 4)

    val (a, b, c) = multiReturnValues()

    defaultParameters(y = "World")

    // 通过扩展方法
    val email = "Ryan.sc.shz@gmail.com"
    email.isEmail()
}

// 变长参数
fun multiParameters(vararg args: Int) {
    args.forEach {
        println(it)
    }
}

// 多返回值的实现
fun multiReturnValues(): Triple<Int, Long, String> {
    return Triple(1, 2L, "Hello")
}

fun defaultParameters(x: Int = 0, y: String = "Hello", z: Long = 0L) {}

// 什么时候使用inline?
// 1. 创建的如果是高阶函数则可以直接加上inline
// 2. 如果对包大小很敏感，则可以只加在频繁调用的高阶函数上
inline fun cost(block: () -> Unit) {
    // 函数使用inline进行内联优化，它有两个好处
    // 1. 减少函数的调用栈，但优化效果可以忽略不计
    // 2. 避免高阶函数时创建额外的函数对象：lambda表达式会额外创建一个函数对象来包括执行体
    val start = System.currentTimeMillis()
    block()
    print("cost: ${System.currentTimeMillis() - start}ms")
}

// 在一个inline高阶函数里，如果函数类型的参数需要作为一个对象在函数里使用
// 则可以加上noinline关键字，避免函数类型的参数被内联优化
inline fun noinline(noinline block: () -> Unit): () -> Unit {
    // 加上noline让block不进行内联
    // 那它才可以继续当成一个对象使用
    println(block.toString())
    // 返回block对象
    return block
}

// lambda中不允许使用return（其实可以return@label的方式指定），只有内联函数的参数才能使用return
// 下面把传递进来的函数类型参数定义为block
// block中如果有return关键字，则返回的是调用noneLocalReturns这个函数的那个函数（简称外层函数）
// 如果block是间接调用(比如在thread中调用)，则无法返回到外层函数，会导致return无法正常工作
// 所以kotlin中不允许在inline函数中间接调用block，编译器会报错
// 但我们可以加上crossinline来解除这种限制，让block可以进行间接的调用，但是block中将不再允许使用return
inline fun noneLocalReturns(crossinline block: () -> Unit) {
    thread {
        block()
    }.start()
}

class FuncTest {
    val func: () -> Unit = fun() {
        
    }
}