package com.ryan.github.kndroid.practice.forjava;

import java.io.IOException;

import kotlin.jvm.Throws;

public class JvmTest {

    private void test() {
        char[] c = new char[1];
        short[] s = new short[1];

        // 1. JvmField
        JvmFieldTest JvmFieldTest = new JvmFieldTest();
        // 使用JvmField
        String name = JvmFieldTest.name;
        // 未使用JvmField
        // bean.getName();

        // 2. JvmName
        JvmNameTest.getTestName();
        JvmNameTest.getTestAge();
        JvmNameTest.setTestAge(2);
        JvmNameTest.sayTest();

        // 3. JvmStatic
        JvmStaticTest.Companion.say();
        JvmStaticTest.say();

        // 4. JvmOverloads
        JvmOverloadsTest test1 = new JvmOverloadsTest("Ryan");
        JvmOverloadsTest test2 = new JvmOverloadsTest("Ryan", 12);
        JvmOverloadsTest test3 = new JvmOverloadsTest("Ryan", 12, 1);
        JvmOverloadsTest test4 = new JvmOverloadsTest("Ryan", 12, 1, "123");

        // 5. Throws
        ThrowsTest throwsTest = new ThrowsTest();
        try {
            throwsTest.closeQuietly(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
