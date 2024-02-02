package Baekjoon.BackTracking;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 미친로봇 / 골드4 / 26분 / 24.02.02
 */

public class BJ_1405_미친로봇 {

    static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[] percentage;
    static boolean[][] visited;
    static double result;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        percentage = new int[4];
        for (int i =0; i < 4; i++) {
            percentage[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[2*N+2][2*N+2];
        result = 0;

        visited[N][N] = true;
        dfs(0, 1, N, N, N);

        System.out.println(result);

    }

    public static void dfs(int cnt, double road,  int x, int y, int N) {
        if (cnt == N) {
            result += road;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int dx = x + directions[i][0];
            int dy = y + directions[i][1];

            if (dx < 0 || dx >= 2 * N + 2 || dy < 0 || dy >= 2 * N + 2) continue;

            if (visited[dx][dy]) continue;

            visited[dx][dy] = true;
            dfs(cnt+1, road*((double)percentage[i]/100.0), dx, dy, N);
            visited[dx][dy] = false;
        }

    }
}
