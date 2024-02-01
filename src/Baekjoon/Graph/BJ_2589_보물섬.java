package Baekjoon.Graph;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 보물섬 / 골드5 / 1시간 / 24.02.01
 */

public class BJ_2589_보물섬 {

    static class Node {
        int x;
        int y;
        int time;
        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static char[][] map;
    static int[][] table;
    static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        table = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j =0; j < line.length(); j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'W') continue;

                bfs(new int[] {i, j}, N, M);
                answer = Math.max(answer, table[i][j]);
            }
        }

        System.out.println(answer);

    }

    public static void bfs(int[] start, int N, int M) {
        boolean[][] visited = new boolean[N][M];

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start[0], start[1], 0));

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if (visited[now.x][now.y]) continue;

            visited[now.x][now.y] = true;

            table[start[0]][start[1]] = Math.max(table[start[0]][start[1]], now.time);

            for (int i =0; i < 4; i++) {
                int dx = now.x + directions[i][0];
                int dy = now.y + directions[i][1];

                if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;

                if (map[dx][dy] == 'W') continue;

                queue.offer(new Node(dx, dy, now.time + 1));
            }
        }
    }
}
