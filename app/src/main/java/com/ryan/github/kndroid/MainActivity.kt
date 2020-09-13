package com.ryan.github.kndroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.ryan.github.kndroid.practice.coroutines.FetchApi
import com.ryan.github.kndroid.practice.coroutines.FetchImage
import com.ryan.github.kndroid.practice.generic.GenericPractice
import com.ryan.github.kndroid.practice.generic.Person
import com.ryan.github.kndroid.practice.generic.Student
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {

    private val statusTv by lazy<TextView> {
        findViewById(R.id.status_tv)
    }

    private val statusIv by lazy<ImageView> {
        findViewById(R.id.status_iv)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scope = CoroutineScope(EmptyCoroutineContext)
        scope.launch(Dispatchers.Main) {
            // 加载网络请求
            statusTv.text = "Loading..."
            val result = FetchApi.load()
            delay(2000)
            statusTv.text = result
            // 加载图片并显示
            val bitmap = FetchImage.get()
            if (bitmap != null) {
                statusIv.setImageBitmap(bitmap)
            }
        }

        testGeneric()
    }

    private fun testGeneric() {
        // 支持逆变
        val test = GenericPractice<Student>()
        val arr = arrayOfNulls<Person>(1)
        val person = Student("Ryan", 22)
        test.fill(arr, person)
    }

    private fun testGenericCopy() {
        // 数组拷贝
        val test = GenericPractice<Person>()
        val from = arrayOf(Student("Ryan", 12))
        val to = arrayOfNulls<Person>(1)
        // from支持协变, to支持逆变
        test.copy(from, to)
    }

}