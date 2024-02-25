package Baekjoon.Simulation;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 주사위굴리기 / 골드4 / 2시간 / 24.02.24
 */

public class BJ_14499_주사위굴리기 {

    static int[][] directions = {{0, 1}, {0,-1}, {-1,0}, {1,0}};
    static int n, m, x,y;
    static int[][] map;
    static int[] dice;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j =0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        dice = new int[7];

        for (int i = 0; i < k; i++) {
            int cmd = Integer.parseInt(st.nextToken());
            int answer = move(cmd);
            if (answer > -1) sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

    public static int move(int d) {
        int dx = x + directions[d-1][0];
        int dy = y + directions[d-1][1];
        if (dx < 0 || dx >= n || dy < 0 || dy >= m) return -1;
        roll(d, dx, dy);
        x = dx;
        y = dy;
        return dice[6];
    }

    public static void roll(int d, int dx, int dy) {
        int now = dice[3];

        switch (d) {
            case 1:
                dice[3] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[2];
                dice[2] = now;
                break;
            case 2:
                dice[3] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[4];
                dice[4] = now;
                break;
            case 3:
                dice[3] = dice[1];
                dice[1] = dice[6];
                dice[6] = dice[5];
                dice[5] = now;
                break;
            case 4:
                dice[3] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[1];
                dice[1] = now;
                break;
            default:
                break;
        }

        if (map[dx][dy] == 0) {
            map[dx][dy] = dice[3];
        }
        else {
            dice[3] = map[dx][dy];
            map[dx][dy] = 0;
        }

    }
}