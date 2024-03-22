package Baekjoon.Simulation;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 낚시왕 / 골드1 / 2시간 / 24.03.22
 */

public class BJ_17143_낚시왕 {

    static final int[] dirX = {0,-1,1,0,0};
    static final int[] dirY = {0,0,0,1,-1};
    static int r,c,m;
    static int[][] map;
    static HashMap<Integer, Shark> sharkMap;

    static private class Shark {
        private int x;
        private int y;
        private int s;
        private int dir;
        private int size;

        public Shark(int x, int y, int s, int dir, int size) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.dir = dir;
            this.size = size;
        }
    }

    private static void move() {
        boolean[] check = new boolean[m+1];
        ArrayList<Integer> removeIdx = new ArrayList<>();


        for (int idx : sharkMap.keySet()) {
            Shark now = sharkMap.get(idx);

            if(map[now.x][now.y] == idx) map[now.x][now.y] = 0;

            // 상어 이동하기
            int nx = now.x;
            int ny = now.y;
            int ns = now.s;
            if (now.dir == 1 || now.dir == 2) {
                ns %= (r-1)*2;
                for (int s = 0; s < ns; s++) {

                    int dx = nx + dirX[now.dir];
                    int dy = ny + dirY[now.dir];

                    // 범위를 벗어나게 되면 다시 방향 바꿈
                    if (dx <= 0 || dx > r) {
                        if (now.dir == 1) now.dir = 2;
                        else if (now.dir == 2) now.dir = 1;

                        dx = nx + dirX[now.dir];
                        dy = ny + dirY[now.dir];
                    }

                    nx = dx;
                    ny = dy;
                }
            }
            else {
                ns %= (c-1)*2;
                for (int s = 0; s < ns; s++) {
                    int dx = nx + dirX[now.dir];
                    int dy = ny + dirY[now.dir];

                    // 범위를 벗어나게 되면 다시 방향 바꿈
                    if (dy <= 0 || dy > c) {
                        if (now.dir == 3) now.dir = 4;
                        else if (now.dir == 4) now.dir = 3;

                        dx = nx + dirX[now.dir];
                        dy = ny + dirY[now.dir];
                    }

                    nx = dx;
                    ny = dy;
                }
            }


            // 해당 칸에 원래 상어가 있는지 확인
            if (map[nx][ny] > 0 && check[map[nx][ny]]) {
                // 이미 이동을 끝마치고 있는 상어라면 크기 비교하기
                int originalSharkIdx = map[nx][ny];

                int originalSharkSize = sharkMap.get(originalSharkIdx).size;

                if (originalSharkSize < now.size) {
                    map[nx][ny] = idx;
                    removeIdx.add(originalSharkIdx);
                }
                else {
                    removeIdx.add(idx);
                    continue;
                }
            } else {
                map[nx][ny] = idx;
            }

            sharkMap.put(idx, new Shark(nx, ny, now.s, now.dir, now.size));
            check[idx] = true;
        }

        for (int i = 0; i < removeIdx.size(); i++) {
            sharkMap.remove(removeIdx.get(i));
        }
    }

    public static void main(String[] args)  throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 상어의 수

        map = new int[r+1][c+1];

        sharkMap = new HashMap<>();

        for (int i =1; i < m+1; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            sharkMap.put(i, new Shark(x, y, s, d, z));
            map[x][y] = i;
        }

        int answer = 0; // 낚시왕이 잡은 상어 크기의 합

        // 낚시왕 이동
        for (int i = 1; i <= c; i++) {

            // 제일 위에 있는 상어 찾기
            for (int j = 1; j <= r; j++) {
                if (map[j][i] > 0) {
                    int sharkIdx = map[j][i];

                    answer +=sharkMap.get(sharkIdx).size;

                    sharkMap.remove(sharkIdx);
                    map[j][i] = 0;
                    break;
                }
            }

            // 상어 이동
            if (sharkMap.size() > 0) move();
        }

        System.out.println(answer);
    }

    public static void print() {
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
