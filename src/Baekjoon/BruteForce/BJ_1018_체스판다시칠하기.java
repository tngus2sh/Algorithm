package Baekjoon.BruteForce;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 체스판다시칠하기 / 실버4 / 1시간 / 24.01.30
 */

public class BJ_1018_체스판다시칠하기 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];

        int result = 2501;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int version1 = 0;
        int version2 = 0;
        int k =0;
        int l = 0;
        while (k + 7 < N && l + 7 < M) {
            for (int i = k; i < k + 8; i++) {
                for (int j = l; j < l + 8; j++) {
                    if (i % 2 == 0) {
                        if (j % 2 == 0 && map[i][j] == 'B') version1++;
                        else if (j % 2 != 0 && map[i][j] == 'W') version1++;
                    } else {
                        if (j % 2 == 0 && map[i][j] == 'W') version1++;
                        else if (j % 2 != 0 && map[i][j] == 'B') version1++;
                    }
                }
            }

            version2 = 64 - version1;

            result = Math.min(result, version1);
            result = Math.min(result, version2);

            if (l + 8 == M) {
                l = 0;
                k++;
            }
            else {
                l++;
            }
            version1 = 0;
        }


        System.out.println(result);
    }
}

