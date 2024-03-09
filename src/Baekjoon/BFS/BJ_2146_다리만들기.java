package Baekjoon.BFS;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 다리만들기 / 골드3 / 1시간 / 24.03.09
 */

public class BJ_2146_다리만들기 {

    static final int[][] directions = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    static int[][] map;
    static boolean[][] visited;
    static int n, minDist;

    static class Node {
        int x;
        int y;
        int dist;
        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    private static void divideSector(int cnt, int color, int x, int y) {
        if (cnt == n*n) {
            return;
        }

        for (int d = 0; d < 4; d++) {
            int dx = x + directions[d][0];
            int dy = y + directions[d][1];

            if (dx < 0 || dx >= n || dy < 0 || dy >= n) continue;

            if (visited[dx][dy]) continue;

            if (map[dx][dy] == 0) continue;

            visited[dx][dy] = true;
            map[dx][dy] = color;

            divideSector(cnt+1, color, dx, dy);
        }
    }

    private static void findBridge(int startX, int startY, int color) {
        boolean[][] isSelected = new boolean[n][n];
        isSelected[startX][startY] = true;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startX, startY, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (map[now.x][now.y] != color && map[now.x][now.y] != 0) {
                minDist = Math.min(minDist, now.dist);
            }

            if (minDist < now.dist) continue;

            for (int d = 0; d < 4; d++) {
                int dx = now.x + directions[d][0];
                int dy = now.y + directions[d][1];

                if (dx < 0 || dx >= n || dy < 0 || dy >= n) continue;

                if (isSelected[dx][dy]) continue;

                if (map[dx][dy] == color) continue;

                isSelected[dx][dy] = true;
                queue.offer(new Node(dx, dy, now.dist+1));
            }
        }
    }


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        map = new int[n][n];

        boolean[][] isEdge = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][n];
        int color = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) continue;

                for (int d = 0; d < 4; d++) {
                    int dx = i + directions[d][0];
                    int dy = j + directions[d][1];

                    if (dx < 0 || dx >= n || dy < 0 || dy >= n) continue;

                    if (isEdge[dx][dy]) continue;

                    if (map[dx][dy] > 0) continue;

                    isEdge[dx][dy] = true;
                }

                if (visited[i][j]) continue;

                map[i][j] = color;
                divideSector(0, color++, i, j);
            }
        }

        minDist = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!isEdge[i][j]) continue;

                int startColor = 0;
                for (int d = 0; d < 4; d++) {
                    int dx = i + directions[d][0];
                    int dy = j + directions[d][1];

                    if (dx < 0 || dx >= n || dy < 0 || dy >= n) continue;

                    if (map[dx][dy] == 0) continue;

                    startColor = map[dx][dy];
                    break;
                }

                findBridge(i, j, startColor);
            }
        }

        System.out.println(minDist);


    }
}