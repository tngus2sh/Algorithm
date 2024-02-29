package Baekjoon.DP;

import java.io.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 01타일 / 실버3 / 10분 / 24.02.29
 */

public class BJ_1904_01타일 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] answer = new int[n+1];

        answer[0] = 1;
        answer[1] = 1;
        for (int i = 2; i <= n; i++) {
            answer[i] = (answer[i-1]%15746) + (answer[i-2]%15746);
        }

        System.out.println(answer[n]%15746);

    }
}
