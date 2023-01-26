package com.tngus2sh.baekjoon.step7.q10250;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int[] result = new int[T];

        for(int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int roomNum = 0;
            if((N % height) == 0) {
                roomNum += (N / height);
                roomNum += (height)*100;
            } else {
                roomNum += (N / height) + 1;
                roomNum += (N % height)*100;
            }
            result[t] = roomNum;
        }
        for(int i = 0; i < T; i++) {
            System.out.println(result[i]);
        }
    }
}
