package Baekjoon.BFS;

import java.io.*;
import java.util.*;


/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 말이되고픈원숭이 / 골드3 / 59분 / 24.03.10
 */

public class BJ_1600_말이되고픈원숭이 {

    static final int[] horseDirX = {2,2,-2,-2,1,1,-1,-1};
    static final int[] horseDirY = {-1,1,-1,1,-2,2,-2,2};
    static final int[] dirX = {1,-1,0,0};
    static final int[] dirY = {0,0,1,-1};
    static int[][] map;

    private static class Node {
        int x;
        int y;
        int cnt;
        int dist;
        public Node(int x, int y, int cnt, int dist) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.dist = dist;
        }
    }


    private static int findRoad(int h, int w, int k) {
        boolean[][][] visited =  new boolean[h][w][k+1];
        for (int i = 0; i< k+1; i++) visited[0][0][i] = true;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0,0, 0, 0));

        while(!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.x == h-1 && now.y == w-1) {
                return now.dist;
            }

            if (now.cnt < k) {
                for (int d = 0; d < 8; d++) {
                    int dx = now.x + horseDirX[d];
                    int dy = now.y + horseDirY[d];

                    if (dx < 0 || dx >= h || dy < 0 || dy >= w) continue;

                    if (visited[dx][dy][now.cnt+1]) continue;

                    if (map[dx][dy]==1) continue;

                    visited[dx][dy][now.cnt+1] = true;
                    queue.offer(new Node(dx, dy, now.cnt+1, now.dist+1));
                }
            }

            for (int d = 0; d < 4; d++) {
                int dx = now.x + dirX[d];
                int dy = now.y + dirY[d];

                if (dx < 0 || dx >= h || dy < 0 || dy >= w) continue;

                if (visited[dx][dy][now.cnt]) continue;

                if (map[dx][dy]==1) continue;

                visited[dx][dy][now.cnt] = true;
                queue.offer(new Node(dx, dy, now.cnt, now.dist+1));
            }
        }
        return -1;
    }


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        map = new int[h][w];

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = findRoad(h, w, k);

        System.out.println(result);

    }
}
