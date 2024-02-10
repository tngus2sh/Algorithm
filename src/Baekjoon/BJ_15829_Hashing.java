package Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * Hashing /  /  /
 */

public class BJ_15829_Hashing {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());

        String str = br.readLine();

        int r = 31;
        int M = 1234567891;
        int answer = 0;
        for (int i = 0; i < str.length(); i++) {
            int a = str.charAt(i) - 96;
            answer += a * Math.pow(r, i);
        }

        System.out.println(answer%M);

    }
}
