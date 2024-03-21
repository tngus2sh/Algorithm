package Baekjoon.Simulation;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 치즈 / 골드4 /  / 24.03.21
 */

public class BJ_2636_치즈 {

    static final int[] dirX = {0, 0, 1, -1};
    static final int[] dirY = {1, -1, 0, 0};
    static int[][] map;
    static int cheese;

    private static boolean isMelting(int n, int m, int time) {
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0,0});

        int nowCheese = 0;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int d = 0; d < 4; d++) {
                int dx = now[0] + dirX[d];
                int dy = now[1] + dirY[d];

                if (dx < 0 || dx >= n || dy < 0 || dy >= m) continue;

                if (visited[dx][dy]) continue;

                if (map[dx][dy] == -time) continue;

                if (map[dx][dy] == 1) {
                    map[dx][dy] = -time;
                    nowCheese++;
                    continue;
                }

                visited[dx][dy] = true;
                queue.offer(new int[] {dx, dy});
            }
        }

        if (nowCheese == 0) return true;
        else {
            cheese = nowCheese;
            return false;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cheese = 0;

        int time = 1;
        while (!isMelting(n, m, time)) {
            time++;
        }

        System.out.println((time-1));
        System.out.println(cheese);

    }
}
