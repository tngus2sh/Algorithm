package Baekjoon.MST;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 최소스패닝트리 / 골드4 /  / 24.01.18
 */

public class BJ_1197_최소스패닝트리_Kruskal {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] graph = new int[E][3];

        for (int i =0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            graph[i][0] = Integer.parseInt(st.nextToken());
            graph[i][1] = Integer.parseInt(st.nextToken());
            graph[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(graph, (o1, o2) -> o1[2] - o2[2]);

        int[] parent = new int[V+1];
        for (int i =0; i < parent.length; i++) parent[i] = i;

        kruskal(graph, parent);

    }

    public static void kruskal(int[][] graph, int[] parent) {
        int cost = 0;
        for (int i = 0; i < graph.length; i++) {
            if (find(parent, graph[i][0]) != find(parent, graph[i][1])) {
                cost += graph[i][2];
                union(parent, graph[i][0], graph[i][1]);
            }
        }

        System.out.println(cost);
    }

    public static void union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);

        if (x < y) parent[y] = x;
        else parent[x] = y;
    }

    public static int find(int[] parent, int x) {
        if (parent[x] == x) return x;
        else return find(parent, parent[x]);
    }
}
