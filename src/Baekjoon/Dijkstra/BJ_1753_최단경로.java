package Baekjoon.Dijkstra;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 최단경로 / 골4 / 18:45 / 231211
 */

public class BJ_1753_최단경로 {

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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점의 개수 : V, 간선의 개수 : E
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        ArrayList<Node>[] graph = new ArrayList[V+1];
        for (int i =0; i < V+1; i++) graph[i] = new ArrayList<>();

        for (int i =0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(K, 0));

        boolean[] check = new boolean[V + 1];
        int[] dist = new int[V+1];

        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[K] = 0;

        while(!pq.isEmpty()) {
            int nowVertex = pq.poll().index;

            if (check[nowVertex]) continue;
            check[nowVertex] = true;

            for (Node next : graph[nowVertex]) {
                if (dist[next.index] > dist[nowVertex] + next.cost) {
                    dist[next.index] = dist[nowVertex] + next.cost;

                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }

        for (int i =1; i < V+1; i++) {
            if (i == K) System.out.println(0);
            else if (dist[i] == INF) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }
}
