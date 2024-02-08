package Baekjoon.BruteForce;

import java.io.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * N_Queen / 골드4 / 1시간 / 24.02.08
 */

public class BJ_9663_N_Queen {

    public static final int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    public static boolean[][] map;
    public static int[] history;
    public static int answer;

    public static void recur(int n, int y) {
        if (y == n) {
            answer++;
            return;
        }


        for (int x = 0; x < n; x++) {
            boolean flag = false;
            for (int j = 0; j < y; j++) {
                int ny = j;
                int nx = history[j];

                // 1. 가로
                if (x == nx) {
                    flag = true;
                    break;
                }

                // 2. 대각선
                if (Math.abs(Math.abs(x) - Math.abs(nx)) == Math.abs(Math.abs(y) - Math.abs(ny))) {
                    flag = true;
                    break;
                }
            }

            if (flag) continue;

            history[y] = x;
            map[x][y] = true;
            recur(n, y+1);
            map[x][y] = false;
            history[y] = -1;
        }

    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        map = new boolean[n][n];
        history = new int[n];
        answer = 0;

        recur(n, 0);

        System.out.println(answer);


    }
}
