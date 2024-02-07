package Baekjoon.BruteForce;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 고층건물 / 골드4 / 1시간 / 24.02.07
 */

public class BJ_1027_고층건물 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] buildings = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i =0; i < N; i++) {
            int cnt = 0;
            double tmp = 0;

            for (int j = i - 1; j >= 0; j--) {
                double slope = (double) (buildings[i] - buildings[j])/(i-j);

                if (j == i - 1 || tmp > slope) {
                    cnt++;
                    tmp = slope;
                }
            }

            for (int j = i + 1; j < N; j++) {
                double slope = (double) (buildings[i] - buildings[j]) / (i - j);

                if ( j == i + 1 || tmp < slope) {
                    cnt++;
                    tmp = slope;
                }
            }

            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}
