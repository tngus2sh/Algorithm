package Baekjoon.DP;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 연속합 / 실버2 / 5분 / 24.02.02
 */

public class BJ_1912_연속합 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Integer.parseInt(st.nextToken());

            if (i > 0) {
                dp[i] = Math.max(dp[i-1] + dp[i], dp[i]);
            }
        }

        Arrays.sort(dp);

        System.out.println(dp[n-1]);

    }
}
