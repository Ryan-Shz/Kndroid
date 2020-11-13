package com.ryan.github.kndroid.practice.generic;

import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JavaGeneric {

    public static void main(String[] args) {
        // TextView是Button的父类！
        TextView button = new Button(null); // Right!

        // 子类的泛型 不属于 泛型 的子类
        List<TextView> textViews = new ArrayList<>();
        List<Button> buttons = new ArrayList<>();
        // Java中的泛型具有不变性
        // textViews = buttons; // Error!


        // 使用extends 让泛型协变
        List<? extends TextView> textViews1 = new ArrayList<>();
        List<Button> buttons1 = new ArrayList<>();
        // 协变点
        textViews1 = buttons1; // Right!
        // 限制1：协变后只能向外提供泛型数据，可以理解为是生产者
        TextView tv = textViews1.get(0);
        // 限制2：协变后不能添加数据
        Button b1 = new Button(null);
        // textViews1.add(b1);


        // 使用super 让泛型逆变
        List<? super Button> buttons2 = new ArrayList<>();
        List<TextView> textViews2 = new ArrayList<>();
        // 逆变点
        buttons2 = textViews2;
        // 限制1：逆变后不能向外提供数据
        // Button b2 = buttons2.get(0);
        // 限制2：逆变后可以添加数据，可以理解为是生产者
        Button b3 = new Button(null);
        buttons.add(b3);

        // ？等价于 ？extends Object
        List<?> textViews3 = new ArrayList<>();
        List<Button> buttons3 = new ArrayList<>();
        textViews3 = buttons3;
    }
}
