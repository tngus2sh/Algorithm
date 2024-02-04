package Baekjoon.Graph;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 저울 / 골드4 / 30분 / 24.02.04
 */

public class BJ_10159_저울 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[][] product = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            product[a][b] = 1;

        }

        for (int k = 1; k < product.length; k++) {
            for (int i = 1; i < product.length; i++) {
                for (int j = 1; j < product.length; j++) {
                    if (i == j) continue;
                    if (product[i][j] > 0) continue;
                    if (product[i][k] == 0 || product[k][j] == 0) continue;

                    product[i][j] = product[i][k] + product[k][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < product.length; i++) {
            int answer = 0;
            for (int j = 1; j < product.length; j++) {
                if (i == j) continue;
                if (product[i][j] == 0 && product[j][i] == 0) answer++;
            }
            sb.append(answer).append("\n");
        }

        System.out.print(sb.toString());

    }
}