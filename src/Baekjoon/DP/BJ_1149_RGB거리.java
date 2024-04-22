package Baekjoon.DP;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * RGB거리 / 실버1 / 10분 / 24.04.19
 */

public class BJ_1149_RGB거리 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] color = new int[N][3];
        int[][] dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            color[i][0] = r;
            color[i][1] = g;
            color[i][2] = b;
        }

        for (int i = 0; i < 3; i++) {
            dp[0][i] = color[0][i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j]  = Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]) + color[i][j];
            }
        }

        int result = dp[N-1][0];

        for (int i = 1; i < 3; i++) {
            result = Math.min(result, dp[N-1][i]);
        }

        System.out.println(result);

    }
}
