package Baekjoon.BackTracking;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 넴모넴모(Easy) / 골드5 / 1시간 / 24.03.31
 */

public class BJ_14712_넴모넴모 {

    static int[][] map;
    static boolean[][] visited;
    static int N, M, answer;

    /* 사각형이 만들어지면 true 반환, 만들어지지 않으면 false 반환 */
    private static boolean isRectangle(int cnt) {

        if (cnt < 4) return false;

        for (int i =0; i < N-1; i++) {
            for (int j = 0; j < M-1; j++) {
                if (visited[i][j] && visited[i][j+1] && visited[i+1][j] && visited[i+1][j+1]) return true;
            }
        }
        return false;
    }

    private static void search(int cnt, int start) {

        answer += isRectangle(cnt)  ? 0 : 1;

        if (cnt == N*M) {
            return;
        }

        for (int i = start; i < N*M; i++) {
            int dx = i / M;
            int dy = i % M;

            if (visited[dx][dy]) continue;

            visited[dx][dy] = true;
            search(cnt+1, i+1);
            visited[dx][dy] = false;
        }
    }


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        answer = 0;

        search(0,0);

        System.out.println(answer);

    }
}
