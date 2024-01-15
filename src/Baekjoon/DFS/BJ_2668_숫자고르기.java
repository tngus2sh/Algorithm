package Baekjoon.DFS;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 숫자고르기 / 골드5 / 2시간 / 24.01.15
 */

public class BJ_2668_숫자고르기 {

    public static int N;
    public static int[] adj;
    public static boolean[] visited;
    public static ArrayList<Integer> result;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 인접 행렬
        adj = new int[N+1];
        for (int i =1; i < N+1; i++) {
            adj[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[N+1];
        result = new ArrayList<>();

        for (int i = 1; i < N+1; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            dfs(i,  i);
            visited[i] = false;
        }

        Collections.sort(result);

        System.out.println(result.size());
        for (int i =0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    public static void dfs(int next, int first) {

        if (!visited[adj[next]]) {
            visited[adj[next]] = true;
            dfs(adj[next], first);
            visited[adj[next]] = false;
        }

        // 처음 나온 숫자랑 같다면 list에 추가
        if (adj[next] == first) {
            result.add(adj[next]);
        }
    }
}