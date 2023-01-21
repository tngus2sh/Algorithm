package com.tngus2sh.baekjoon.class1.q10818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        try {
            int N = Integer.parseInt(buf.readLine().trim());
            StringTokenizer st = new StringTokenizer(buf.readLine().trim());
            // 숫자 받아들이기
            int num = 0;
            // 최대값
            int max = 0;
            // 최소값
            int min = 0;
            // 인덱스
            int index = 0;
            while(st.hasMoreTokens()) {
                num = Integer.parseInt(st.nextToken());
                if(index > 0 ) {
                    max = Math.max(num, max);
                    min = Math.min(num, min);
                } else {
                    max = num;
                    min = num;
                    index = 1;
                }
            }
            System.out.println(min + " " + max);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
