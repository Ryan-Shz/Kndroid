package com.ryan.github.kndroid.practice.annotation;

import java.util.Scanner;

public class CodeGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int result = NthPrime(n);
        Integer.toHexString(result);
    }

    private static int NthPrime(int n) {
        int i = 2, j = 1;
        while (true) {
            j++;
            if (j > i / j) {
                n--;
                if (n == 0) {
                    break;
                }
                j = 1;
            }
            if (i % j == 0) {
                i++;
                j = 1;
            }
        }
        return i;
    }
}
