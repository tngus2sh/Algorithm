package Baekjoon.BackTracking;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 색종이붙이기 / 골드2 / 2시간 / 24.03.29
 */

public class BJ_17136_색종이붙이기 {

    static final int[] paper = {0, 5, 5, 5, 5, 5};
    static int[][] map;
    static int ans;

    private static boolean isAttach(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i < 0 || i >= 10 || j < 0 || j >= 10) return false;

                if (map[i][j] == 0) return false;
            }
        }
        return true;
    }

    private static void attach(int x, int y, int size, int state) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = state;
            }
        }
    }

    private static void dfs(int x, int y, int cnt) {

        if (x >= 9 && y > 9) {
            ans = Math.min(ans, cnt);
            return;
        }

        if (ans <= cnt) {
            return;
        }

        if (y > 9) {
            dfs(x+1, 0, cnt);
            return;
        }

        if (map[x][y] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (paper[i] > 0 && isAttach(x, y, i)) {
                    attach(x, y, i, 0);
                    paper[i]--;
                    dfs(x, y+1, cnt + 1);
                    attach(x, y, i, 1);
                    paper[i]++;
                }
            }
        }
        else {
            dfs(x, y+1, cnt);
        }

    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[10][10];

        for (int i  = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = Integer.MAX_VALUE;

        dfs(0,0, 0);

        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }
}
