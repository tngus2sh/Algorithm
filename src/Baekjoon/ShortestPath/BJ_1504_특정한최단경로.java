package Baekjoon.ShortestPath;

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
    // 최소 거리 최댓값
    static final int INF = 200000001;
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

        // 반드시 지나가야하는 정점
        st = new StringTokenizer(br.readLine());
        int vertex1 = Integer.parseInt(st.nextToken());
        int vertex2 = Integer.parseInt(st.nextToken());

        // 1 -> vertex1 -> vertex2 -> N
        int res1 = Dijkstra(N, 1, vertex1);
        res1 += Dijkstra(N, vertex1, vertex2);
        res1 += Dijkstra(N, vertex2, N);

        // 1 -> vertex2 -> vertex1 -> N
        int res2 = Dijkstra(N, 1, vertex2);
        res2 += Dijkstra(N, vertex2, vertex1);
        res2 += Dijkstra(N, vertex1, N);

        int ans = (res1 >= INF || res2 >= INF) ? -1 : Math.min(res1, res2);

        System.out.println(ans);
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
