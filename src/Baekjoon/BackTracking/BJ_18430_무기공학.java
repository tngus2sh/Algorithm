package Baekjoon.BackTracking;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 무기공학 / 골드4 /  / 24.04.05
 */

public class BJ_18430_무기공학 {
    static int N, M, ans;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findBoomerang(0, 0);
        System.out.println(ans);
    }

    public static void findBoomerang(int idx, int tmpSum) {
        if (idx == N*M) {
            ans = Math.max(ans, tmpSum);
            return;
        }

        int x = idx/M;
        int y = idx%M;

        if (!visited[x][y]) {
            // ┑
            if (x+1 < N && y-1 >= 0 && !visited[x+1][y] && !visited[x][y-1]) {
                visited[x+1][y] = true;
                visited[x][y] = true;
                visited[x][y-1] = true;
                findBoomerang(idx+1, tmpSum + map[x+1][y] + map[x][y-1] + (map[x][y]*2));
                visited[x+1][y] = false;
                visited[x][y] = false;
                visited[x][y-1] = false;
            }
            // ┙
            if (x-1 >= 0 && y-1 >= 0 && !visited[x-1][y] && !visited[x][y-1]) {
                visited[x-1][y] = true;
                visited[x][y] = true;
                visited[x][y-1] = true;
                findBoomerang(idx+1, tmpSum + map[x-1][y] + map[x][y-1] + (map[x][y]*2));
                visited[x-1][y] = false;
                visited[x][y] = false;
                visited[x][y-1] = false;
            }
            // ┕
            if (x-1 >= 0 && y+1 < M && !visited[x-1][y] && !visited[x][y+1]) {
                visited[x-1][y] = true;
                visited[x][y] = true;
                visited[x][y+1] = true;
                findBoomerang(idx+1, tmpSum + map[x-1][y] + map[x][y+1] + (map[x][y]*2));
                visited[x-1][y] = false;
                visited[x][y] = false;
                visited[x][y+1] = false;
            }
            // ┍
            if (x+1 < N && y+1 < M && !visited[x+1][y] && !visited[x][y+1]) {
                visited[x+1][y] = true;
                visited[x][y] = true;
                visited[x][y+1] = true;
                findBoomerang(idx+1, tmpSum + map[x+1][y] + map[x][y+1] + (map[x][y]*2));
                visited[x+1][y] = false;
                visited[x][y] = false;
                visited[x][y+1] = false;
            }
        }

        findBoomerang(idx+1, tmpSum);
    }
}
