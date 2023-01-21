package com.tngus2sh.baekjoon.class1.q8958;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(buf.readLine());

        // 결과
        int[] result = new int[T];


        for(int t = 0; t < T; t++) {
            try {
                String oxs = buf.readLine();
                // 연속 체크
                int seq = 1;

                for(int i = 0; i < oxs.length(); i++) {
                    char ox = oxs.charAt(i);
                    // O일 때 seq만큼 결과값에 넣어준다.
                    if (ox == 'O') {
                        result[t] += seq++;
                    } else {
                        seq = 1;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for(int i = 0; i < T; i++) {
            System.out.println(result[i]);
        }
    }
}
