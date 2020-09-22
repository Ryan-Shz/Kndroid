package com.ryan.github.kndroid.practice.type

fun main() {
    val a = 1
    var b = 2
    b = 3

    val c = 1L
    val d = 1L

    var e = 1
    var f: Long = e.toLong()


    val g = 1u

    val i = """
        <!doctype html>
        <html>
          <head>
            <meta charset="UTF-8"
            <title>Hello World</title>
          </head>
          <body>
            <div id = "container">
              <H1>Hello World</H1>
              <p>This is a demo page</p>
            </div>
          </body>
        </html>
    """.trimIndent()
    println(i)


    val arr1 = intArrayOf(1, 2)
    val arr2 = arrayOf(1, 2)

    var intRange = 1..10
    intRange.joinToString(",")

    val z = 1.442424242424
    val floatRange = 1f..2f
    z in floatRange

    1..Int.MAX_VALUE
}

