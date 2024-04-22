package Baekjoon.DP;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 1로만들기2 / 실버1 / 16분 / 24.04.22
 */

public class BJ_12852_1로만들기2 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];
        int[] trace = new int[N+1];

        dp[0] = dp[1] = 0;

        for (int i = 2; i < N+1; i++) {
            dp[i] = dp[i-1] + 1;
            trace[i] = i-1;
            if (i % 3 == 0 && dp[i] > dp[i/3] + 1) {
                dp[i] = dp[i/3] + 1;
                trace[i] = i/3;
            }
            if (i % 2 == 0 && dp[i] > dp[i/2] + 1) {
                dp[i] = dp[i/2] + 1;
                trace[i] = i/2;
            }
        }

        System.out.println(dp[N]);

        StringBuilder sb = new StringBuilder();

        int start = N;
        sb.append(N).append(" ");
        while (start != 1) {
            sb.append(trace[start]).append(" ");
            start = trace[start];
        }

        System.out.println(sb.toString().trim());


    }
}
