package Baekjoon.Greedy;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 센서 / 골드5 / 45분 / 24.05.01
 */

public class BJ_2212_센서 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] input = new int[N];
        int[] diff = new int[N-1];

        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);

        for (int i = 0; i < N-1; i++) {
            diff[i] = input[i+1] - input[i];
        }

        Arrays.sort(diff);

        if (N <= K) {
            System.out.println(0);
            return;
        }

        int result =0;
        for (int i = 0; i < N-K; i++) {
            result += diff[i];
        }

        System.out.println(result);


    }
}
