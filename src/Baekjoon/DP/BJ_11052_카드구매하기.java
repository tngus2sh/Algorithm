package Baekjoon.DP;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 카드구매하기 / 실버1 /  / 24.04.22
 */

public class BJ_11052_카드구매하기 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] P = new int[N+1];
        int[] dp = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i =1; i < N+1; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i] , dp[i-j] + P[j]);
            }
        }

        System.out.println(dp[N]);

    }
}