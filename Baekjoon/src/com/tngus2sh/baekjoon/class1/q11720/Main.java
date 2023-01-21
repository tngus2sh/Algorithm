package com.tngus2sh.baekjoon.class1.q11720;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        int sum = 0;
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        try {
            int N  = Integer.parseInt(buf.readLine().trim());
            String s = buf.readLine();
            for(int i = 0; i < N; i++) {
                sum += Integer.parseInt(String.valueOf(s.charAt(i)));
//                sum += (s.charAt(i) - '0');
            }
            System.out.println(sum);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
