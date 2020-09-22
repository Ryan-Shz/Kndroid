package com.ryan.github.kndroid.practice.coroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ryan.github.kndroid.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.coroutine_layout)

        val testIv1 = findViewById<ImageView>(R.id.text_iv1)
        val testIv2 = findViewById<ImageView>(R.id.text_iv2)
        val testBtn = findViewById<Button>(R.id.test_btn)
        lifecycleScope.launch {

            // 1. 使用async加载远程图片
            try {
                val bitmap = async(Dispatchers.IO) { loadBitmap() }
                testIv1.setImageBitmap(bitmap.await())
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // 2. 使用协程加载远程图片
            val bitmap = loadBitmapD()
            testIv2.setImageBitmap(bitmap)
        }

        // 弹出对话框
        testBtn.setOnClickListener {
            launchWithLifecycle {
                val result = showConfirmDialog("提示", "你确定要退出吗？")
                toast("result: $result")
            }
        }
    }

    // 同步加载一个远程图片
    private fun loadBitmap(): Bitmap? {
        val client = OkHttpClient.Builder()
            .build()
        val request = Request.Builder()
            .url("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3907780498,1745800971&fm=26&gp=0.jpg")
            .get()
            .build()
        val response = client.newCall(request).execute()
        val body = response.body
        val stream = body?.byteStream()
        return BitmapFactory.decodeStream(stream)
    }

    // 将回调改造成协程同步的方式
    private suspend fun loadBitmapD(): Bitmap? = suspendCoroutine {
        val client = OkHttpClient.Builder()
            .build()
        val request = Request.Builder()
            .url("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3907780498,1745800971&fm=26&gp=0.jpg")
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                it.resumeWithException(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val bitmap = BitmapFactory.decodeStream(response.body?.byteStream())
                if (bitmap != null) {
                    it.resumeWith(Result.success(bitmap))
                } else {
                    it.resumeWith(Result.failure(Exception("bitmap is null!")))
                }
            }
        })
    }
}