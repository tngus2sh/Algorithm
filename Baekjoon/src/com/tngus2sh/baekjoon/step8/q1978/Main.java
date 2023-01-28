package com.tngus2sh.baekjoon.step8.q1978;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int cnt = N;

        for(int n = 0; n < N; n++) {
            int num = Integer.parseInt(st.nextToken());

            if(num == 1){
                cnt--;
            }
            for(int i = 2; i < num-1; i++) {
                if(num % i == 0) {
                    cnt--;
                    break;
                }
            }
        }
        System.out.println(cnt);
    }
}
