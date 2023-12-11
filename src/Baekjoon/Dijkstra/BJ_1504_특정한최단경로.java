package Baekjoon.Dijkstra;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 특정한최단경로 / 골드4 / 31:52 / 231211
 */

public class BJ_1504_특정한최단경로 {

    public static class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static ArrayList<Node>[] graph;
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for (int i =0; i < N+1; i++) graph[i] = new ArrayList<>();

        for (int i =0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int vertex1 = Integer.parseInt(st.nextToken());
        int vertex2 = Integer.parseInt(st.nextToken());

        int res1 = Dijkstra(N, 1, vertex1);
        int res2 = Dijkstra(N, vertex1, vertex2);
        int res3 = Dijkstra(N, vertex2, N);

        int res4 = Dijkstra(N, 1, vertex2);
        int res5 = Dijkstra(N, vertex2, vertex1);
        int res6 = Dijkstra(N, vertex1, N);

        if ((res1 == INF || res2 == INF || res3 == INF)
                && (res4 == INF || res5 == INF || res6 == INF )) {
            System.out.println(-1);
        } else if (res1 == INF || res2 == INF || res3 == INF) {
            System.out.println(res4 + res5 + res6);
        } else if (res4 == INF || res5 == INF || res6 == INF) {
            System.out.println(res1 + res2 + res3);
        } else {
            System.out.println(Math.min(res1+res2+res3, res4+res5+res6));
        }
    }

    public static int Dijkstra(int N, int start, int end) {
        boolean[] check = new boolean[N+1];
        int[] dist = new int[N+1];

        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()) {
            int nowVertex = pq.poll().index;

            if (check[nowVertex]) continue;
            check[nowVertex] = true;

            for (Node next : graph[nowVertex]) {
                if(dist[next.index] > dist[nowVertex] + next.cost) {
                    dist[next.index] = dist[nowVertex] + next.cost;

                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }

        return dist[end];
    }
}
