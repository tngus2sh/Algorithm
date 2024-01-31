package Baekjoon.BFS;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 상범빌딩 / 골드5 / 20분 / 24.01.31
 */

public class BJ_6593_상범빌딩 {

    static class Node {
        int x;
        int y;
        int z;
        int time;
        public Node() {}
        public Node(int x, int y, int z, int time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }
    }

    static final int[][] directions = {{0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}};
    static char[][][] map;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) break;

            map = new char[L][R][C];

            Node start = new Node();
            Node end = new Node();

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String line = br.readLine();
                    for (int k = 0; k < line.length(); k++) {
                        map[i][j][k] = line.charAt(k);

                        if (map[i][j][k] == 'S') {
                            start.x = i;
                            start.y = j;
                            start.z = k;
                            start.time = 0;
                        }
                        else if (map[i][j][k] == 'E') {
                            end.x = i;
                            end.y = j;
                            end.z = k;
                        }
                    }
                }

                br.readLine();
            }

            int answer = bfs(start, end, L, R, C);

            if (answer == -1) sb.append("Trapped!").append("\n");
            else sb.append("Escaped in ").append(answer).append(" minute(s).").append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int bfs(Node start, Node end, int L, int R, int C) {

        boolean[][][] visited = new boolean[L][R][C];

        Queue<Node> queue = new LinkedList<>();
        queue.offer(start);

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.x == end.x && now.y == end.y && now.z == end.z) {
                return now.time;
            }

            if (visited[now.x][now.y][now.z]) continue;

            visited[now.x][now.y][now.z] = true;

            for (int i = 0; i < directions.length; i++) {
                int dx = now.x + directions[i][0];
                int dy = now.y + directions[i][1];
                int dz = now.z + directions[i][2];

                if (dx < 0 || dx >= L || dy < 0 || dy >= R || dz < 0 || dz >= C) continue;

                if (map[dx][dy][dz] == '#') continue;

                queue.offer(new Node(dx, dy, dz, now.time + 1));
            }

        }


        return -1;
    }
}
