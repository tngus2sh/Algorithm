package Baekjoon.BFS;

import java.io.*;
import java.util.*;

public class BJ_14940_쉬운최단거리 {

    static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int[][] map, result;
    static int n, m;

    static void bfs(int[] start) {
        result[start[0]][start[1]] = 0;
        boolean[][] visited = new boolean[n][m];
        visited[start[0]][start[1]] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {start[0], start[1], 0});

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            result[now[0]][now[1]] = now[2];

            for (int i = 0; i < 4; i++) {
                int dx = now[0] + directions[i][0];
                int dy = now[1] + directions[i][1];

                if (dx < 0 || dx >= n || dy < 0 || dy >= m) continue;

                if(visited[dx][dy]) continue;

                if (map[dx][dy] == 0) continue;

                visited[dx][dy]= true;
                queue.offer(new int[]{dx, dy, now[2] + 1});
            }
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        result = new int[n][m];
        int[] start = new int[2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    start[0] = i;
                    start[1] = j;
                    result[i][j] = -1;
                }
                else if (map[i][j] == 1) {
                    result[i][j] = -1;
                }
            }
        }

        bfs(start);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

}
