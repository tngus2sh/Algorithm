package Baekjoon.DP;

import java.io.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 축구 / 골드4 / 17분 / 24.02.14
 */

public class BJ_1344_축구 {

    static final int[] noPrime = {0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18};

    public static double per(int[][] comb, double a) {
        double total = 0;
        for (int i = 0; i < noPrime.length; i++) {
            int now = noPrime[i];
            total += comb[18][now] * Math.pow(a, now) * Math.pow(1.0-a, 18.0-now);
        }

        return total;
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double a = Double.parseDouble(br.readLine())/100.0;
        double b = Double.parseDouble(br.readLine())/100.0;

        int[][] comb = new int[19][19];
        comb[1][0] = 1;
        comb[1][1] = 1;
        for (int i = 2; i < 19; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = comb[i-1][j] + comb[i-1][j-1];
            }
        }

        double answer = 0;
        answer += per(comb, a);
        answer *= per(comb, b);

        answer = 1.0 - answer;

        System.out.println(answer);
    }
}
