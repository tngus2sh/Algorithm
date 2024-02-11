package Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * ㄷㅓㅇ치 /  /  /
 */

public class BJ_7568_ㄷㅓㅇ치 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        int[] weight = new int[n];
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            height[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            answer[i]++;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                if (weight[i] < weight[j] && height[i] < height[j] ) {
                    answer[i]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < answer.length; i++) {
            sb.append(answer[i]).append(" ");
        }

        System.out.println(sb.toString().trim());

    }
}