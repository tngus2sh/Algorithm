package Baekjoon.BFS;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 효율적인해킹 / 실버1 / 40분 / 24.09.27
 */

public class BJ_1325_효율적인해킹 {

    private static int N, M;
    private static List<Integer>[] adj;
    private static boolean[] isVisited;
    private static int[] visitedArr;

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
        M = Integer.parseInt(st.nextToken()); // 컴퓨터 신뢰 관계 개수

        adj = new ArrayList[N+1];

        for (int i = 0; i < N+1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
        }

        visitedArr = new int[N+1];

        for (int i = 1; i < N+1; i++) {
            isVisited = new boolean[N+1];
            bfs(i);
        }

        StringBuilder sb = new StringBuilder();
        int maxVal = 0;

        for (int i = 1; i < N+1; i++) {
            if (visitedArr[i] > maxVal) {
                maxVal = visitedArr[i];
                sb.setLength(0); // sb 초기화
                sb.append(i).append(" ");
            }
            else if (visitedArr[i] == maxVal) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb.toString().trim());

    }

    private static void bfs(int start) {

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        isVisited[start] = true;

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : adj[now]) {
                if (isVisited[next]) continue;

                isVisited[next] = true;
                visitedArr[next]++;

                queue.offer(next);
            }
        }
    }
}
