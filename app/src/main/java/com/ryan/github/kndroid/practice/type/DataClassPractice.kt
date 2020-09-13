package com.ryan.github.kndroid.practice.type

import com.google.gson.Gson

/**
 * created by 2020/9/14 12:29 AM
 *
 * @author Ryan
 */
class DataClassPractice {

    companion object {
        fun test() {
            val person = Person("Ryan", 12)
            val gson = Gson()
            val dataJson = gson.toJson(person)
            val (name, age) = gson.fromJson<Person>(dataJson, Person::class.java)
            println("name: $name, age: $age")
        }
    }
}