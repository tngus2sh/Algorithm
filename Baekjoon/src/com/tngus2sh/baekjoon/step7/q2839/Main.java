package com.tngus2sh.baekjoon.step7.q2839;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        // 3kg 5kg 봉지 >> 더 적은 개수의 봉지 가져가야함
        int num = 0;

        int r = N / 5;
        int test = 0;

        for(int i = r; i >= 0; i--) {
            test = N - (5*i);
            if((test % 3 == 0)) {
                num = (i + (test / 3));
                break;
            }
            num = -1;
        }

        System.out.println(num);
    }
}
