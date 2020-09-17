package com.ryan.github.kndroid.practice.forjava

fun testa() {

}

class JvmFieldTest {

    /**
     * 默认生成的java代码name是private的，并且getter和setter是public的
     *
     * @JvmField 会自动将setter、getter访问器消除掉，并且把这个字段修改为public
     */
    @JvmField
    val name = "Ryan"

    var age = 12
}