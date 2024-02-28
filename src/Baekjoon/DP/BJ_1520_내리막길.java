package Baekjoon.DP;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 내리막길 / 골드3 / 2시간 / 24.02.28
 */

public class BJ_1520_내리막길 {

    static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[][] map, dp;
    static int m, n;

    public static int dfs(int x, int y, int height) {
        if (x == m-1 && y == n-1) {
            return 1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;
        for (int d = 0; d < 4; d++) {
            int dx = x + directions[d][0];
            int dy = y + directions[d][1];

            if (dx < 0 || dx >= m || dy < 0 || dy >= n) continue;

            if (map[dx][dy] >= height) continue;

            dp[x][y] += dfs(dx, dy, map[dx][dy]);

        }
        return dp[x][y];
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[m][n];
        for (int i =0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        int answer = dfs(0,0, map[0][0]);

        System.out.println(answer);
    }
}
