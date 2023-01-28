package com.tngus2sh.baekjoon.step7.q2775;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArrayMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        int[] result = new int[T];

        for(int t = 0; t < T; t++) {
            int[][] num = new int[15][15];

            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            for(int i = 0; i <= k; i++) {
                num[i][1] = 1;
            }

            for(int i = 0; i <= k; i++) {
                for(int ii = 2; ii <= n; ii++) {
                    if(i != 0) {
                        num[i][ii] = num[i][ii - 1] + num[i - 1][ii];
                    } else {
                        num[i][ii] = ii;
                    }
                }
            }

            result[t] = num[k][n];
        }

        for(int i = 0; i < T; i++) {
            System.out.println(result[i]);
        }
    }
}
