package Baekjoon.DFS;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 바이러스 / 실버3 / 20분 / 24.09.27
 */

public class BJ_2606_바이러스 {

    private static int N, M, comp;
    private static List<Integer>[] adj;
    private static boolean[] isVisited;
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        M = Integer.parseInt(br.readLine()); // 컴퓨터 쌍의 개수

        adj = new ArrayList[N+1]; //  인접한 컴퓨터 리스트

        for (int i = 0; i < N+1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        isVisited = new boolean[N+1];
        comp = 0;

        isVisited[1] = true;
        // dfs
        dfs(1, 0);

        System.out.println(comp);
        
    }

    private static void dfs(int now, int cnt) {

        if (cnt == N) {
            return;
        }


        for (int i = 0; i < adj[now].size(); i++) {

            int nowVal = adj[now].get(i);

            if (isVisited[nowVal]) continue;

            isVisited[nowVal] = true;
            comp++;

            dfs(nowVal, cnt+1);
        }
    }
}
