package com.ryan.github.kndroid.practice.type

import android.util.Log
import android.view.View
import com.ryan.github.kndroid.TAG

fun printViewId(view: View?) {
    Log.i(TAG, "view id is: ${view?.id}")
}
