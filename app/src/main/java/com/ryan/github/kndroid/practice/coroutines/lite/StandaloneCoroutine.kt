package com.ryan.github.kndroid.practice.coroutines.lite

import kotlin.coroutines.CoroutineContext

class StandaloneCoroutine<T>(context: CoroutineContext, block: suspend () -> T) :
    AbstractCoroutine<T>(context, block)