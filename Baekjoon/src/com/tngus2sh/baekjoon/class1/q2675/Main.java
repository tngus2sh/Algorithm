package com.tngus2sh.baekjoon.class1.q2675;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int t = 0; t < T; t++) {
            // 반복횟수
            int loop = sc.nextInt();

            // 문자열
            String s = sc.next();

            // 문자열이 없을 때
            if(s.isEmpty()) {
                continue;
            } else {
                // 새로운 문자열
                StringBuffer newS = new StringBuffer();

                for (int i = 0; i < s.length(); i++) {
                    for (int ii = 0; ii < loop; ii++) {
                        char c = s.charAt(i);
                        newS.append(c);
                    }
                }
                System.out.println(newS);
            }
        }
    }
}

