package Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 가장긴바이토닉부분수열 / 골드4 / 1시간 / 24.02.08
 */

public class BJ_11054_가장긴바이토닉부분수열 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] input = new int[n];
        int[] left = new int [n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());

            if (i > 0) {
                for (int j = i-1; j >= 0; j--) {
                    if (input[j] < input[i]) {
                        left[i] = Math.max(left[j] + 1, left[i]);
                    }
                }
            }
        }

        right[n-1] = 0;
        for (int i = n-2; i >= 0; i--) {
            for (int j = i+1; j < n; j++) {
                if (input[j] < input[i]) {
                    right[i] = Math.max(right[j] + 1, right[i]);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, left[i] + right[i]);
        }

        System.out.println(answer + 1);
    }
}
