package Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 벽부수고이동하기 / 골드3 / 46분 / 24.02.25
 */

public class BJ_2206_벽부수고이동하기 {

    static class Node {
        int x;
        int y;
        int dist;
        int isBroken;
        public Node(int x, int y, int dist, int isBroken) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.isBroken = isBroken;
        }
    }
    static final int[][] directions = {{1,0}, {0,1}, {-1, 0}, {0, -1}};
    static int[][] map;

    public static int bfs(int n, int m) {
        boolean[][][] visited = new boolean[n+1][m+1][2];
        visited[1][1][0] = true;
        visited[1][1][1] = true;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(1,1,1, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.x == n && now.y == m) {
                return now.dist;
            }

            for (int d = 0; d < 4; d++) {
                int dx = now.x + directions[d][0];
                int dy = now.y + directions[d][1];

                if (dx < 1 || dx > n || dy < 1 || dy > m) continue;

                if (visited[dx][dy][now.isBroken]) continue;

                if (map[dx][dy] == 1) {
                    if (now.isBroken == 0) {
                        visited[dx][dy][now.isBroken] = true;
                        queue.offer(new Node(dx, dy, now.dist + 1, 1));
                    }
                }
                else {
                    visited[dx][dy][now.isBroken] = true;
                    queue.offer(new Node(dx, dy, now.dist + 1, now.isBroken));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n+1][m+1];

        for (int i = 1; i < n+1; i++) {
            String input = br.readLine();
            for (int j = 1; j < m+1; j++) {
                map[i][j] = input.charAt(j-1) - '0';
            }
        }

        int answer = bfs(n, m);

        System.out.println(answer);
    }
}