package Baekjoon.BackTracking;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 선발명단 / 골드5 /  / 24.04.02
 */

public class BJ_3980_선발명단 {

    static int[][] s;
    static boolean[] isSelected;
    static int ans;


    private static void recur(int cnt, int player, int skill) {

        if (cnt == 11) {
            ans = Math.max(ans, skill);
            return;
        }

        for (int i =0; i < 11; i++) {
            if (isSelected[i]) continue;

            if (s[cnt][i] == 0) continue;

            isSelected[i] = true;
            recur(cnt + 1, i, skill + s[cnt][i]);
            isSelected[i] = false;
        }

    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            s = new int[11][11];

            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < 11; j++) {
                    s[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            isSelected = new boolean[11];
            ans = 0;

            recur(0, 0, 0);

            sb.append(ans).append("\n");

        }

        System.out.print(sb);
    }
}
