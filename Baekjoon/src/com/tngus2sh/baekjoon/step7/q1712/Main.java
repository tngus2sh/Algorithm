package com.tngus2sh.baekjoon.step7.q1712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 나눌 때 분모가 0이 되는지를 체크
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

        String s = buff.readLine();

        StringTokenizer st = new StringTokenizer(s);
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int benefit = 0;
        if((C-B) > 0) {
            benefit = ((A/(C-B)) + 1);
        } else {
            benefit = -1;
        }
        System.out.println(benefit);
    }
}
