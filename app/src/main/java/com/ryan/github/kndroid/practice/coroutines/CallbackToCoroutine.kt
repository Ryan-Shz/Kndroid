package com.ryan.github.kndroid.practice.coroutines

import okhttp3.*
import java.io.IOException
import java.lang.Exception
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun testCallbackToCoroutine(): String? {
    val test = CallbackToCoroutine()
    return test.loadByOkHttp()
}

/**
 * 将原本的回调改造成协程的同步返回
 */
class CallbackToCoroutine {

    suspend fun loadByOkHttp(): String? {
        return suspendCoroutine {
            val client = OkHttpClient.Builder()
                .build()
            val request = Request.Builder()
                .url("https://www.devio.org/io/flutter_app/json/home_page.json")
                .get()
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    it.resumeWithException(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val isSuccess = response.isSuccessful
                    val result =
                        if (isSuccess) Result.success(response.body?.string()) else Result.failure(
                            Exception("request failed")
                        )
                    it.resumeWith(result)
                }
            })
        }
    }
}