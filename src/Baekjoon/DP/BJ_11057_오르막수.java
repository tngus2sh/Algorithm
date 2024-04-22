package Baekjoon.DP;

import java.io.*;


/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 오르막수 / 실버1 /  / 24.04.19
 */

public class BJ_11057_오르막수 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][10];

        for (int i = 0; i < 10; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = j; k < 10; k++) {
                    dp[i][j] += dp[i-1][k];
                    dp[i][j] %= 10007;
                }
            }
        }

        System.out.println(dp[N][0] % 10007);

    }
}