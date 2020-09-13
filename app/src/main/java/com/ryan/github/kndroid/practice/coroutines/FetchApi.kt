package com.ryan.github.kndroid.practice.coroutines

import android.widget.TextView
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import kotlin.coroutines.EmptyCoroutineContext

/**
 * created by 2020/9/13 10:48 PM
 *
 * @author Ryan
 */
class FetchApi {

    companion object {

        suspend fun load(): String? {
            return try {
                withContext(Dispatchers.IO) {
                    val url = "https://www.devio.org/io/flutter_app/json/home_page.json"
                    val client = OkHttpClient.Builder()
                        .build()
                    val request = Request.Builder()
                        .url(url)
                        .get()
                        .build()
                    val response = client.newCall(request).execute()
                    response.body?.string()
                }
            } catch (e: Exception) {
                "load failed: ${e.message}"
            }
        }
    }
}