package com.ryan.github.kndroid.practice.type

class Person(val name: String, val age: Int, private val sex: Int) {

    override fun equals(other: Any?): Boolean {
        val other = other as? Person ?: return false
        return this.name == other.name
                && this.age == other.age
                && this.sex == other.sex
    }

    override fun hashCode(): Int {
        return 1 + 7 * age + 13 * name.hashCode() + 9 * sex
    }
}