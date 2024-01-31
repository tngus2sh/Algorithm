package Baekjoon.DP;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 스티커 / 실버1 / 2시간 / 24.01.31
 */

public class BJ_9465_스티커 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            int[][] stickers = new int[2][n+1];
            int[][] dp = new int[2][n+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n ;i++) {
                stickers[0][i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i =1; i <= n; i++) {
                stickers[1][i] = Integer.parseInt(st.nextToken());
            }

            dp[0][1] = stickers[0][1];
            dp[1][1] = stickers[1][1];
            for (int j = 2; j <= n; j++) {
                    dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + stickers[0][j];
                    dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + stickers[1][j];
            }

            sb.append(Math.max(dp[0][n], dp[1][n])).append("\n");

        }

        System.out.println(sb.toString());

    }
}
