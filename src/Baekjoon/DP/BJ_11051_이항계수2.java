package Baekjoon.DP;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 이항계수2 / 실버2 / 20분 / 24.01.18
 */

public class BJ_11051_이항계수2 {
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] combination = new int[N+1][N+1];

        combination[1][0] = 1;
        combination[1][1] = 1;

        for (int i = 2; i < N+1; i++) {
            combination[i][0] = 1;
            for (int j = 1; j < i+1; j++) {
                combination[i][j] = (combination[i-1][j] + combination[i - 1][j - 1]) % 10_007;
            }
        }

        System.out.println(combination[N][K] % 10_007);

    }
}
