package Baekjoon.DP;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 동전2 / 골드5 / 25분 / 24.03.15
 */

public class BJ_2294_동전2 {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] input = new int[n];

        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[k+1];
        Arrays.fill(dp, 100_001);
        dp[0] = 0;

        for (int i = 1; i < k+1; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= input[j]) {
                    dp[i] = Math.min(dp[i], dp[i - (input[j])] + 1);
                }
            }
        }

        if (dp[k] == 100_001) System.out.println(-1);
        else System.out.println(dp[k]);
    }
}
