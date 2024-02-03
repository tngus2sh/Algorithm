package Baekjoon.BruteForce;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 고층건물 / 골드4 /  / 24.01.29
 */

public class BJ_1027_고층건물 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<long[]> index = new ArrayList<>();
        long[] buildings = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        int i = 0;
        int L = 0;
        int R = 0;
        long result = 0;
        while (i < buildings.length) {

            int k = i-1;
            for (; k >= 0; k--) {
                if (buildings[i] <= buildings[k]) {
                    break;
                }
            }
            L = i - k;

            int nextIdx = buildings.length;
            for (int j = i + 1; j < buildings.length; j++) {
                if (buildings[i] <= buildings[j]) {
                    nextIdx = j;
                    break;
                }
            }

            R = nextIdx - i;

            result = Math.max(result, L + R);
            i = nextIdx;

            System.out.println("L : " + L + ", R : " + R);
            L = 0;
            R = 0;
        }

        System.out.println(result);
    }
}
