package Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 인구이동 / 골드4 /  / 24.02.19
 */

public class BJ_16234_인구이동 {

    static final int[][] dir = {{1,0}, {0,1}};
    static final int[][] dir2 = {{1,0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] selected;

    public static int[] bfs(int N, int[] start, int[][] map) {
        boolean[][] visited = new boolean[N][N];

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {start[0], start[1], 0, 0});

        int[] answer = new int[2];

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            answer[0] = now[2];
            answer[1] = now[3];

            for (int d = 0; d < 4; d++) {
                int dx = now[0] + dir2[d][0];
                int dy = now[1] + dir2[d][1];

                if (dx < 0 || dx >= N || dy < 0 || dy >= N) continue;

                if (visited[dx][dy]) continue;

                if (!selected[dx][dy]) continue;

                visited[dx][dy] = true;
                queue.offer(new int[] {dx, dy, now[2] + 1, now[3] + map[dx][dy]});
            }
        }

        return answer;
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        selected = new boolean[N][N];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                for (int d = 0; d < 2; d++) {
                    int dx = i + dir[d][0];
                    int dy = j + dir[d][1];

                    if (dx < 0 || dx >= N || dy < 0 || dy >= N) continue;

                    if (selected[dx][dy]) continue;

                    int val = Math.abs(map[i][j] - map[dx][dy]);
                    if (L <= val && val <= R) {
                        selected[dx][dy] = true;
                    }
                }
            }
        }
    }
}
