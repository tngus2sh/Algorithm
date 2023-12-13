package Baekjoon.Dijkstra;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 백도어 / 골드5 / 31:40 / 231213
 */

public class BJ_17396_백도어 {
    public static class Node implements Comparable<Node> {
        int index;
        long cost;
        public Node(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N : 분기점, M : 분기점들을 잇는 길의 개수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 지나가지 말아야 하는 분기점 배열
        Map<Integer, Boolean> canSee = new HashMap<>();

        // 분기점들이 시야에서 보이는지 -> 0: 안 보임, 1: 보임
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());
            if (number == 1 && i < N-1) canSee.put(i, true);
            else canSee.put(i, false);
        }

        ArrayList<Node>[] graph = new ArrayList[N];
        for (int i =0; i < N; i++) graph[i] = new ArrayList<>();

        for (int i =0 ; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            // 시야에 보이는 분기점이라면 그대로 넘어가기
            if (canSee.get(a) || canSee.get(b)) continue;

            graph[a].add(new Node(b,t));
            graph[b].add(new Node(a,t));
        }

        boolean[] check = new boolean[N];
        long[] dist = new long[N];
        long INF = 30000000001L;

        Arrays.fill(dist, INF);
        dist[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

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

        long res = dist[N-1] == INF ? -1 : dist[N-1];
        System.out.println(res);
    }
}