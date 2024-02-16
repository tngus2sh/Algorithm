package Baekjoon.TwoPointers;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 부분합 / 골드4 / 1시간 45분 / 24.02.16
 */

public class BJ_1806_부분합 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] input = new int[n+1];

        st = new StringTokenizer(br.readLine());

        boolean theEnd = false;
        for(int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int total = 0;
        while (start <= n && end <= n) {
            if (total >= s && min > end - start) min = end - start;

            if (total < s) total += input[end++];
            else total -= input[start++];
        }

        if (min == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(min);
    }
}

