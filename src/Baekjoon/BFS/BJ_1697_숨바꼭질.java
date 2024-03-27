package Baekjoon.BFS;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 숨바꼭질 / 실버1 / 11분 / 24.03.27
 */

public class BJ_1697_숨바꼭질 {

    private static class Subin {
        int x;
        int time;

        public Subin(int x, int time){
            this.x = x;
            this.time = time;
        }
    }

    private static int bfs(int n, int k) {
        int[] visited = new int[100_001];
        Arrays.fill(visited, Integer.MAX_VALUE);

        Queue<Subin> queue = new LinkedList<>();
        queue.offer(new Subin(n, 0));

        while (!queue.isEmpty()) {
            Subin now = queue.poll();

            if (now.x == k) {
                return now.time;
            }

            if (n < k) {
                if (now.x + 1 < 100_001 && visited[now.x + 1] >= now.time+1) {
                    visited[now.x + 1] = now.time;
                    queue.offer(new Subin(now.x+1, now.time +1));
                }
                if (now.x * 2 < 100_001 && visited[now.x*2] >= now.time+1) {
                    visited[now.x * 2] = now.time;
                    queue.offer(new Subin(now.x * 2, now.time + 1));
                }
            }
            if (now.x - 1 >= 0 && visited[now.x-1] >= now.time+1) {
                visited[now.x-1] = now.time+1;
                queue.offer(new Subin(now.x - 1, now.time + 1));
            }

        }

        return -1;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int result = bfs(n, k);

        System.out.println(result);

    }
}