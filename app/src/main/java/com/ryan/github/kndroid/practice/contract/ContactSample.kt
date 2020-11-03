package com.ryan.github.kndroid.practice.contract

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ExperimentalContracts
fun main() {
    val s: String? = ""
    if (s.isNotNull()) {
        println(s.length)
    }

    var ret: Int
    runFun {
        ret = 15;
    }
    println(ret)
}

@ExperimentalContracts
fun String?.isNotNull(): Boolean {
    contract {
        returns(true) implies (this@isNotNull != null)
    }
    return this != null
}

@ExperimentalContracts
fun runFun(action: () -> Unit) {
    contract {
        callsInPlace(action, InvocationKind.EXACTLY_ONCE)
    }
    action()
}
