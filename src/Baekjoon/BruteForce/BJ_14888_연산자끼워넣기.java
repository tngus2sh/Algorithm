package Baekjoon.BruteForce;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 연산자끼워넣기 / 실버1 / 1시간 / 24.01.13
 */

public class BJ_14888_연산자끼워넣기 {

    public static int N;
    public static int[] arr;
    public static int[] operation;
    public static int max;
    public static int min;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i =0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        operation = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i =0; i < 4; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        recur(arr[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    public static void recur(int result, int num) {
        if (num == N) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i =0; i < 4; i++) {
            if (operation[i] <= 0) continue;

            // [백트래킹]
            // 해당 연산자 사용시 하나 줄여준다.
            operation[i]--;

            switch(i) {

                case 0: recur(result + arr[num], num+1); break;
                case 1: recur(result - arr[num], num+1); break;
                case 2: recur(result * arr[num], num+1); break;
                case 3: recur(result / arr[num], num+1); break;
                default: break;

            }

            // 재귀 끝난 후 연산자 값 하나 올려준다.
            operation[i]++;
        }
    }
}
