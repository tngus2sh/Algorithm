package Baekjoon.MST;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 최소스패닝트리 / 골드4 /  / 24.01.18
 */

class Edge implements Comparable<Edge> {
    int w;
    int cost;
    public Edge(int w, int cost) {
        this.w = w;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o){
        return this.cost - o.cost;
    }
}
public class BJ_1197_최소스패닝트리_Prim {

    static List<Edge>[] graph;

    public static void prim(int start, int n) {
        boolean[] visit = new boolean[n+1];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        int total = 0;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int v = edge.w;
            int cost = edge.cost;

            if (visit[v]) continue;

            visit[v] = true;
            total += cost;

            for (Edge e : graph[v]) {
                if (!visit[e.w]) {
                    pq.offer(e);
                }
            }
        }

        System.out.println(total);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V+1];
        for(int i =0; i < V+1; i++) graph[i] = new ArrayList<>();

        for (int i =0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[v].add(new Edge(w, cost));
            graph[w].add(new Edge(v, cost));
        }

        prim(1, V);

    }
}
