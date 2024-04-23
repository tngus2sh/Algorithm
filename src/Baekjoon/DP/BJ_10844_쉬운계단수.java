package Baekjoon.DP;

import java.io.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 쉬운계단수 / 실버1 / 39분 / 24.04.23
 */

public class BJ_10844_쉬운계단수 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long MOD = 1_000_000_000;

        long[][] dp = new long[N+1][10];

        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1L;
        }

        for (int i = 2; i < N+1; i++) {
            dp[i][0] = dp[i-1][1];

            for (int j = 1; j <= 8; j++) {
                dp[i][j] = (dp[i-1][j-1] % MOD) + (dp[i-1][j+1] % MOD);
            }

            dp[i][9] = dp[i-1][8];
        }

        long result = 0;

        for (int i = 1; i <= 9; i++) {
            result = (result + (dp[N][i] % MOD)) % MOD;
        }

        System.out.println(result);

    }
}
