package Baekjoon.Simulation;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 경사로 / 골드3 / 1시간18분 / 24.03.07
 */

public class BJ_14890_경사로 {

    static final int[][] directions = {{1, 0}, {0, 1}};
    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int dx = 0; dx < N; dx++) {
            int dy = 0;
            int val = map[dx][dy];
            boolean isPossible = true;
            int slope = -1;

            while (dy < N) {
                if (map[dx][dy] != val) {
                    // 경사로 차이가 1인 경우
                    if (Math.abs(map[dx][dy] - val) == 1) {
                        // 1. 앞에 있는 높이가 더 높은 경우
                        if (map[dx][dy] - val == 1) {
                            for (int ny = dy-1; ny >= dy - L; ny--) {
                                if (ny < 0 || map[dx][ny] != val || slope >= ny) {
                                    isPossible = false;
                                    break;
                                }
                            }
                            if (isPossible) val = map[dx][dy];
                        }
                        // 2. 앞에 있는 높이가 더 낮은 경우
                        else {
                            for (int ny = dy; ny <= dy + L -1; ny++) {
                                if (ny >= N || map[dx][ny] != map[dx][dy] ) {
                                    isPossible = false;
                                    break;
                                }
                            }
                            if(isPossible) {
                                val = map[dx][dy];
                                slope = dy + L - 1;
                                dy += L - 1;
                            }
                        }
                    }
                    else {
                        isPossible = false;
                        break;
                    }
                }
                dy++;
            }

            if (isPossible) answer++;
        }

        for (int dy = 0; dy < N; dy++) {
            int dx = 0;
            int val = map[dx][dy];
            boolean isPossible = true;
            int slope = -1;

            while (dx < N) {
                if (map[dx][dy] != val) {
                    // 경사로 차이가 1인 경우
                    if (Math.abs(map[dx][dy] - val) == 1) {
                        // 1. 앞에 있는 높이가 더 높은 경우
                        if (map[dx][dy] - val == 1) {
                            for (int nx = dx-1; nx >= dx - L; nx--) {
                                if (nx < 0 || map[nx][dy] != val || slope >= nx) {
                                    isPossible = false;
                                    break;
                                }
                            }
                            if (isPossible) val = map[dx][dy];
                        }
                        // 2. 앞에 있는 높이가 더 낮은 경우
                        else {
                            for (int nx = dx; nx <= dx + L -1; nx++) {
                                if (nx >= N || map[nx][dy] != map[dx][dy] ) {
                                    isPossible = false;
                                    break;
                                }
                            }
                            if(isPossible) {
                                val = map[dx][dy];
                                slope = dx + L - 1;
                                dx += L - 1;
                            }
                        }
                    }
                    else {
                        isPossible = false;
                        break;
                    }
                }
                dx++;
            }

            if (isPossible) answer++;
        }

        System.out.println(answer);

    }
}
