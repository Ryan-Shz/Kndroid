package com.ryan.github.kndroid.practice.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*
import kotlin.coroutines.createCoroutine

open class BaseActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        launch(Dispatchers.Main) {

        }

        lifecycleScope.launch {

        }

        launch {
            supervisorScope {

            }
        }

        suspend {
            println("123")
        }

//        suspend {
//
//        }.createCoroutine()

    }

    suspend fun testA() {

    }

    protected fun launch(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch(block = block)
    }
}