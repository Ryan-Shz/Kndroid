package com.ryan.github.kndroid.practice.coroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 * created by 2020/9/13 10:51 PM
 *
 * @author Ryan
 */
class FetchImage {

    companion object {
        const val url =
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3907780498,1745800971&fm=26&gp=0.jpg"

        suspend fun get(): Bitmap? {
            return withContext(Dispatchers.IO) {
                val client = OkHttpClient.Builder()
                    .build()
                val request = Request.Builder()
                    .url(url)
                    .get()
                    .build()
                val response = client.newCall(request).execute()
                val body = response.body
                val stream = body?.byteStream()
                if (stream != null) {
                    BitmapFactory.decodeStream(stream)
                } else {
                    null
                }
            }
        }
    }
}