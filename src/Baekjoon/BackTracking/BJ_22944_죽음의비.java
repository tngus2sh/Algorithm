package Baekjoon.BackTracking;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 죽음의비 / 골드3 / 2시간 / 24.05.02
 */

public class BJ_22944_죽음의비 {

    private static class Node {
        int x;
        int y;
        int um;
        int hp;
        int dis;
        public Node (int x, int y, int um, int hp, int dis) {
            this.x = x;
            this.y = y;
            this.um = um;
            this.hp = hp;
            this.dis = dis;
        }
    }

    private static final int[] dirX = {0,0,1,-1};
    private static final int[] dirY = {1,-1,0,0};
    private static int move;
    private static char[][] map;

    private static void bfs(int N, int H, int D, int startX, int startY) {
        int[][] visited = new int[N][N];
        visited[startX][startY] = H;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(startX, startY, 0, H, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            for (int i =0; i < 4; i++) {
                int um = now.um, hp = now.hp, dis = now.dis;
                int dx = now.x + dirX[i];
                int dy = now.y + dirY[i];

                if (dx < 0 || dx >= N || dy < 0 || dy >= N) continue;

                if (map[dx][dy] == 'E') {
                    move = now.dis + 1;
                    return;
                }

                // 1. 우산 있는 경우
                if (map[dx][dy] == 'U') um = D;

                // 2. 죽음의 비를 맞는 경우
                if (um != 0) um--;
                else hp--;

                if (hp == 0) continue;

                if (visited[dx][dy] < um + hp) {
                    visited[dx][dy] = um + hp;
                    queue.offer(new Node(dx, dy, um, hp, dis+1));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        map = new char[N][N];

        int startX = 0;
        int startY = 0;

        for (int i =0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j);

                if (map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
            }
        }

        move = -1;

        bfs(N, H, D, startX, startY);

        System.out.println(move);

    }
}