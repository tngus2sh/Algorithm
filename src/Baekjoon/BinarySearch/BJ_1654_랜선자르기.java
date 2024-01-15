package Baekjoon.BinarySearch;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 랜선자르기 / 실버2 / 50분 / 24.01.15
 */

public class BJ_1654_랜선자르기 {

    public static long[] lines;
    public static long max;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        lines = new long[K];
        long length = 0;

        for (int i = 0; i  < K; i++) {
            lines[i] = Long.parseLong(br.readLine());
            length += lines[i];
        }

        int end = (int)(length / N);

        max = Integer.MIN_VALUE;

        int sum = 0;
        for (int i =0; i < K; i++) {
            sum += (int)(lines[i] / end);
        }
        if (sum == N) max = end;
        else binarySearch(1, end, K, N);

        System.out.println(max);
    }

    public static void binarySearch(long first, long end, int K, int N) {

        long mid = (first + end)/2;

        if (first > end) {
            max = Math.max(max, mid);
            return;
        }

        int sum = 0;
        for (int i =0; i < K; i++) {
            sum += (int)(lines[i] / mid);
        }

        if (sum == N) {
            max = Math.max(max, mid);
            binarySearch(mid+1, end, K, N);
        }
        else if (sum < N) {
            binarySearch(first, mid-1, K, N);
        }
        else if (sum > N) {
            binarySearch(mid+1, end, K, N);
        }
    }
}