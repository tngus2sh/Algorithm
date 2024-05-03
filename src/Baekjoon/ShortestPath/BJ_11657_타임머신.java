package Baekjoon.ShortestPath;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 타임머신 / 골드4 / 30분 / 24.05.03
 */

public class BJ_11657_타임머신 {

    private static class Edge {
        int v;
        int w;
        long cost;
        public Edge (int v, int w, long cost) {
            this.v = v;
            this.w = w;
            this.cost = cost;
        }
    }

    private static int N, M;
    private static ArrayList<Edge> graph;
    private static final long INF = Long.MAX_VALUE;

    private static void ford() {
        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Edge edge = graph.get(j);

                if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
                    dist[edge.w] = dist[edge.v] + edge.cost;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            Edge edge = graph.get(i);

            if (dist[edge.v] != INF && dist[edge.w] > dist[edge.v] + edge.cost) {
                System.out.println(-1);
                System.exit(0);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 2; i < dist.length; i++) {
            if (dist[i] == INF) {
                sb.append(-1);
            }
            else {
                sb.append(dist[i]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.add(new Edge(v, w, cost));
        }

        ford();
    }
}
