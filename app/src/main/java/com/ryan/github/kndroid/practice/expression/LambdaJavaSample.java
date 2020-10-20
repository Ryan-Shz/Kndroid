package com.ryan.github.kndroid.practice.expression;

import android.view.View;

public class LambdaJavaSample {

    public void test() {
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };
        Runnable runnable = () -> {
            System.out.println("Hello");
        };

        View.OnClickListener listener = v -> {

        };
    }
}
