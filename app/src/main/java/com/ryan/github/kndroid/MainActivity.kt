package com.ryan.github.kndroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.ryan.github.kndroid.practice.coroutines.FetchApi
import com.ryan.github.kndroid.practice.coroutines.FetchImage
import com.ryan.github.kndroid.practice.coroutines.testCallbackToCoroutine
import com.ryan.github.kndroid.practice.expression.testStringOperator
import com.ryan.github.kndroid.practice.function.*
import com.ryan.github.kndroid.practice.generic.GenericPractice
import com.ryan.github.kndroid.practice.generic.Person
import com.ryan.github.kndroid.practice.generic.Student
import com.ryan.github.kndroid.practice.type.*
import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

const val TAG = "KAndroid"

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
        dataClassTest()
        testGeneric1()
        testGeneric2()
        testNumberUtilAndCost()
        coroutineTest()
        printViewId(statusIv)
        testStringOperator()
    }

    private fun coroutineTest() {
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
            // 测试Retrofit请求
            retrofitRequest(this@MainActivity)
            // 将回调转换为协程的测试用例
            val res = testCallbackToCoroutine()
            Log.i(TAG, "result: $res")
        }
    }

    private fun testGeneric1() {
        // 支持逆变
        val test = GenericPractice<Student>()
        val arr = arrayOfNulls<Person>(1)
        val person = Student("Ryan", 22)
        test.fill(arr, person)
    }

    private fun testGeneric2() {
        // 数组拷贝
        val test = GenericPractice<Person>()
        val from = arrayOf(Student("Ryan", 12))
        val to = arrayOfNulls<Person>(1)
        // from支持协变, to支持逆变
        test.copy(from, to)
    }

    private fun testNumberUtilAndCost() {
        printlnTimeCost("Array", ::avgNumbersByArray)
        printlnTimeCost("IntArray", ::avgNumbersByArray)
        printlnTimeCost("List", ::avgNumbersByList)

        val divByTreeList = divByTree()
        Log.i(TAG, "div by tree list: $divByTreeList")
    }
}