package Baekjoon.Greedy;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 21세일 / 실버4 / 8분 / 24.01.25
 */

public class BJ_11508_21세일 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] products = new int[N];
        int total = 0;
        for (int i = 0; i < N; i++) {
            products[i] = Integer.parseInt(br.readLine());
            total += products[i];
        }

        Arrays.sort(products);

        int subtract = 0;
        for (int i = products.length - 3; i >= 0; i-=3) {
            subtract += products[i];
        }

        System.out.println(total - subtract);

    }
}
