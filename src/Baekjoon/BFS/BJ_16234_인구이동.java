package Baekjoon.BFS;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 인구이동 / 골드4 / 2시간 / 24.02.20
 */

public class BJ_16234_인구이동 {

    static final int[][] dir = {{1,0}, {0,1}, {-1,0}, {0, -1}};
    static int N, L, R;
    static LinkedList<int[]> list;
    static int[][] map;
    static boolean[][] selected;

    public static int bfs(int[] start) {
        list = new LinkedList<>();
        list.add(start);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {start[0], start[1], map[start[0]][start[1]]});

        int sum = map[start[0]][start[1]];

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int d = 0; d < 4; d++) {
                int dx = x + dir[d][0];
                int dy = y + dir[d][1];

                if (dx < 0 || dx >= N || dy < 0 || dy >= N) continue;

                if (selected[dx][dy]) continue;

                int diff = Math.abs(map[x][y] - map[dx][dy]);

                if (L <= diff && diff <= R) {
                    selected[dx][dy] = true;
                    queue.offer(new int[] {dx, dy});
                    list.add(new int[] {dx, dy});
                    sum += map[dx][dy];
                }
            }
        }
        return sum/list.size();
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        boolean toBeContinued = false;
        while (true) {
            selected = new boolean[N][N];
            toBeContinued = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (selected[i][j]) continue;

                    selected[i][j] = true;
                    int sum = bfs(new int[] {i, j});

                    if (list.size() > 1) {
                        toBeContinued = true;

                        for (int[] now : list) {
                            map[now[0]][now[1]] = sum;
                        }
                    }
                }
            }
            if (!toBeContinued) break;
            time++;
        }

        System.out.println(time);
    }
}