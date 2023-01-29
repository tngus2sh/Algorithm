package com.tngus2sh.baekjoon.step8.q2581;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int sum = 0;
        int min = N;
        boolean isNum;

        for(int i = M; i <= N; i++) {
            isNum = false;
            if(i == 1) {
                continue;
            }
            for(int ii = 2; ii < i-1; ii++) {
                if(i%ii == 0) {
                    isNum = true;
                    break;
                }
            }
            if(!isNum) {
                sum += i;
                min = Math.min(i, min);
            }
        }

        if(sum <= 0) {
            System.out.println(-1);
        } else {
            System.out.println(sum);
            System.out.println(min);
        }
    }
}
