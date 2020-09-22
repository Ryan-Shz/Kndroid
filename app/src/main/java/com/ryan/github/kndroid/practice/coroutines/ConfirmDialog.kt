package com.ryan.github.kndroid.practice.coroutines

import android.content.Context
import androidx.appcompat.app.AlertDialog
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

// 使用协程的方式弹出Android对话框
suspend fun Context.showConfirmDialog(title: String, message: String = "") =
    suspendCoroutine<Boolean> { continuation ->
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setNegativeButton("取消") { _, _ ->
                continuation.resume(false)
            }
            .setPositiveButton("确认") { _, _ ->
                continuation.resume(true)
            }
            .setOnCancelListener {
                continuation.resume(false)
            }
            .show()
    }