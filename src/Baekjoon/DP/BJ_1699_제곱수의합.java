package Baekjoon.DP;

import java.io.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 제곱수의합 / 실버2 /  / 24.10.03
 */

public class BJ_1699_제곱수의합 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];

        for (int i = 1; i <= N; i++) {
            dp[i] = i;

            for (int j = 1; j * j <= i; j++) {
                if (dp[i] > dp[i - (j*j)] + 1) {
                    dp[i] = dp[i - (j*j)] + 1;
                }
            }
        }

        System.out.println(dp[N]);

    }
}
