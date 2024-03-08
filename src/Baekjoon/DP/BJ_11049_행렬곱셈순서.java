package Baekjoon.DP;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 행렬곱셈순서 / 골드3 / 2시간 / 24.03.08
 */

public class BJ_11049_행렬곱셈순서 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n][n];
        int[] data = new int[n + 1];
        int INF  = Integer.MAX_VALUE;

        for (int i  = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            data[i] = r;
            data[i+1] = c;
        }


        for (int i = 2; i < n+1; i++) { // 구간 간격 (2 ~  n) : 2라고 했을 때 0-1, 2-3 이런식으로 나누어짐
            for (int j = 0; j < n-i+1; j++) { // 구간 시작점
                dp[j][j+i-1] = INF;
                for (int k = j; k < j+i-1; k++) { // 중간 지점
                    int value = dp[j][k] + dp[k+1][j+i-1] + (data[j] * data[k+1] * data[j+i]); // 중간지점을 기준으로 최솟값 + 행렬 계산
                    dp[j][j+i-1] = Math.min(dp[j][j+i-1], value);
                }
            }
        }

        System.out.println(dp[0][n - 1]);
    }
}
