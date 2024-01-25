package Baekjoon.BFS;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 숨바꼭질4 / 골드4 / 1시간 55분 / 24.01.25
 */

public class BJ_13913_숨바꼭질4 {

    static class Subin {
        int pos;
        int time;
        public Subin(){}
        public Subin(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

    static Subin result;
    static int[] move;
    static final int INF = 100_001;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        result = new Subin();
        move = new int[INF];

        if (N == K) {
            System.out.println(0);
            System.out.println(N);
        } else {
            bfs(N, K);
            StringBuilder sb = new StringBuilder();
            sb.append(result.time + "\n");
            Stack<Integer> stack = new Stack<>();
            int after = K;
            while (after != N) {
                stack.push(after);
                after = move[after];
            }
            stack.push(N);

            while(!stack.isEmpty()) {
                sb.append(stack.pop()).append(" ");
            }

            System.out.println(sb);
        }
    }

    public static void bfs(int N, int K) {
        int[] visited = new int[INF];

        Queue<Subin> subin = new LinkedList<>();
        subin.offer(new Subin(N, 0));

        while(!subin.isEmpty()) {
            Subin now = subin.poll();

            if (now.pos == K) {
                result = now;
                break;
            }

            if (now.pos - 1 >= 0 && visited[now.pos-1] == 0) {
                visited[now.pos-1] = now.time + 1;
                move[now.pos-1] = now.pos;
                subin.offer(new Subin(now.pos - 1, now.time + 1));
            }
            if (N < K) {
                if (now.pos + 1 < INF && visited[now.pos+1] == 0) {
                    visited[now.pos+1] = now.time+1;
                    move[now.pos+1] = now.pos;
                    subin.offer(new Subin(now.pos + 1, now.time + 1));
                }
                if (now.pos * 2 < INF && visited[now.pos*2] == 0) {
                    visited[now.pos*2] = now.time+1;
                    move[now.pos*2] = now.pos;
                    subin.offer(new Subin(now.pos * 2, now.time + 1));
                }
            }
        }
    }
}