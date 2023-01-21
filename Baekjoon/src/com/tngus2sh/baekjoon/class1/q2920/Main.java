package com.tngus2sh.baekjoon.class1.q2920;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 이전 값
        int before = 0;

        // 이전 결과
        String beDiff = "";

        // 차이 변수
        int diff = 0;

        // 결과 변수
        String result = "";

        // 8개 숫자동안 반복
        for(int i = 0; i < 8; i++) {
            int N = sc.nextInt();

            diff = N - before;

            before = N;

            if(i > 0) {
                if (diff > 0) {
                    result = "ascending";
                } else if (diff < 0) {
                    result = "descending";
                } else {
                    result = "mixed";
                    break;
                }

                if(i > 1) {
                    if (beDiff.equals(result)) {
                        beDiff = result;
                        continue;
                    } else {
                        result = "mixed";
                        break;
                    }
                }
                beDiff = result;
            }
        }
        System.out.println(result);
    }
}
