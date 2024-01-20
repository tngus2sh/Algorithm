package Baekjoon.Greedy;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 알바생강호 / 실버4 / 30분 / 24.01.20
 */

public class BJ_1758_알바생강호 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Integer[] tips = new Integer[N];
        for (int i = 0; i < N; i++) {
            tips[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(tips, Collections.reverseOrder());

        long result =0;
        for (int i = 0; i < tips.length; i++) {
            if (tips[i] - i < 0) break;

            result += tips[i] - i;
        }

        System.out.println(result);
    }
}
