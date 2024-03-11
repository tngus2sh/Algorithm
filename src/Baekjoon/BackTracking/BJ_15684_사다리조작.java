package Baekjoon.BackTracking;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 사다리조작 / 골드3 / 2시간 / 24.03.11
 */

public class BJ_15684_사다리조작 {

    static int[][] map;
    static int n,m,h,ans;
    static boolean isFinish = false;

    private static void dfs(int x, int y, int cnt) {
        if (isFinish) return;
        if (ans == cnt) {
            if (check()) isFinish = true;
            return;
        }

        for (int i = y; i < h+1; i++) {
            for (int j = x; j < n; j++) {
                if (map[i][j] == 0 && map[i][j+1] == 0) {
                    map[i][j] = 1;
                    map[i][j+1] = -1;

                    dfs(1,1, cnt+1);

                    map[i][j] = map[i][j+1] = 0;
                }
            }
        }
    }

    private static boolean check() {
        for (int i = 1; i < n+1; i++) {
            int nx = i;
            int ny = 1;

            while (ny <= h) {
                if (map[ny][nx] == 1) nx++;
                else if (map[ny][nx] == -1) nx--;
                ny++;
            }

            if (nx != i) return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 세로선
        m = Integer.parseInt(st.nextToken()); // 가로선
        h = Integer.parseInt(st.nextToken()); // 가로선을 놓을 수 있는 위치의 개수

        map = new int[h+1][n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1; // 오른쪽
            map[a][b+1] = -1; // 왼쪽
        }

        for (int i = 0; i <= 3; i++) {
            ans = i;
            dfs(1, 1, 0);
            if (isFinish) break;
        }

        System.out.println((isFinish ? ans : -1));
        br.close();

    }
}
