package com.ryan.github.kndroid.practice.coroutines.lite

import kotlin.coroutines.CoroutineContext

fun launch(
    context: CoroutineContext = CommonPool,
    block: suspend () -> Unit
): AbstractCoroutine<Unit> = StandaloneCoroutine(context, block)