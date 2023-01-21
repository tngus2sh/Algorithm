package com.tngus2sh.baekjoon.class1.q2562;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = 0;
        int max = 0;
        int maxIndex = 0;
        for(int i =0; i < 9; i++) {
            N = sc.nextInt();
            if(N >= max) {
                max = N;
                maxIndex = i;
            }
        }
        System.out.println(max);
        System.out.println(maxIndex+1);
    }
}
