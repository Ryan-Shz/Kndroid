package com.ryan.github.kndroid.practice.forjava

import java.io.IOException
import java.io.Writer

class ThrowsTest {
    @Throws(IOException::class)
    fun closeQuietly(output: Writer?) {
        output?.close()
    }
}