package com.tngus2sh.baekjoon.step7.q2775;

import java.util.Scanner;

public class RecursiveMain {

    public static int DP(int k, int n) {
        if(n <= 0) {
            return 0;
        }
        if(k <= 0) {
            return n;
        }

        return DP(k, n-1) + DP(k-1, n);
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        int[] result = new int[T];

        for(int t = 0; t < T;t++) {
            int k = sc.nextInt();
            int n = sc.nextInt();

            result[t] = DP(k,n);
        }

        for(int i = 0; i < T ; i++) {
            System.out.println(result[i]);
        }
    }
}
