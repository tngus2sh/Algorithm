package Baekjoon.DP;

import java.io.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 2xn타일링2 / 실버3 / 2시간 / 24.01.16
 */

public class BJ_11727_2xn타일링2 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 3;
        for (int i =3; i <= n; i++) {
            dp[i] = (dp[i-1] + 2 * dp[i-2]) % 10_007;
        }
        System.out.println(dp[n]);
    }
}
