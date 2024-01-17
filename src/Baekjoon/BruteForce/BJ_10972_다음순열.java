package Baekjoon.BruteForce;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 다음순열 / 실버3 /  / 24.01.17
 */

public class BJ_10972_다음순열 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] input = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        if (np(input)) {
            for (int i =0; i < N; i++) {
                System.out.print(input[i] + " ");
            }
            System.out.println();
        }
        else System.out.println(-1);

    }

    public static boolean np(int[] input) {

        int n = input.length;

        int i = n-1;
        while(i > 0 && input[i -1] >= input[i]) --i;
        if (i == 0) return false;

        int j = n-1;
        while(input[i-1] >= input[j]) --j;

        swap(input, i-1, j);

        int k = n-1;
        while(i < k) {
            swap(input, i++, k--);
        }
        return true;
    }

    public static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }
}
