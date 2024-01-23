package Baekjoon.DP;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 가장큰증가하는부분수열 / 실버2 / 40분 / 24.01.23
 */

public class BJ_11055_가장큰증가하는부분수열 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] array = new int[N+1];
        int[] dp = new int[N+1];

        int result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = array[1];

        for (int i = 1; i < array.length; i++) {
            dp[i] = array[i];
            for (int j = 1; j < i; j++) {
                if (array[j] < array[i]) {
                    dp[i] = Math.max(dp[j] + array[i], dp[i]);
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);

    }
}
