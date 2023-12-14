package Baekjoon.Dijkstra;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 해킹 / 골드4 / 20:00  / 231214
 */

public class BJ_10282_해킹 {

    public static class Node implements Comparable<Node> {
        int index;
        int cost;
        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o){
            return Integer.compare(this.cost, o.cost);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t =0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            ArrayList<Node>[] graph = new ArrayList[n+1];
            for (int i = 0; i < n+1; i++) graph[i] = new ArrayList<>();

            for (int i =0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph[b].add(new Node(a,s));
            }

            boolean[] check = new boolean[n+1];
            int[] dist = new int[n+1];

            int INF = Integer.MAX_VALUE;

            Arrays.fill(dist, INF);
            dist[c] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.offer(new Node(c, 0));

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

            int cnt = 0;
            int time = 0;
            for (int i = 1; i < n+1; i++) {
                if (dist[i] != INF) {
                    time = Math.max(time, dist[i]);
                    cnt++;
                }
            }

            sb.append(cnt);
            sb.append(" ");
            sb.append(time);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
