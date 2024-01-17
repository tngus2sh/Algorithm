package Baekjoon.Dijkstra;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 숨바꼭질3 / 골드5 / 18분 / 24.01.17
 */

public class BJ_13549_숨바꼭질3 {

    public static class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int MAX = 100_001;

        boolean[] check = new boolean[MAX];
        int[] dist = new int[MAX];
        int INF = Integer.MAX_VALUE;

        Arrays.fill(dist, INF);
        dist[N] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(N, 0));

        /*
        초를 가중치로 보고 최단거리 알고리즘 사용하여 진행
        1. X-1과 X+1은 1초가 걸리므로 가중치 1로 설정
        2. 2*X는 0초가 걸리므로 가중치 0으로 설정
         */
        while (!pq.isEmpty()) {
            int nowVertex = pq.poll().index;

            if (nowVertex < 0 || nowVertex > MAX) continue;

            if (check[nowVertex]) continue;

            check[nowVertex] = true;

            // X+1 위치로 이동
            if (nowVertex + 1 < MAX && dist[nowVertex + 1] > dist[nowVertex] + 1) {
                dist[nowVertex + 1] = dist[nowVertex] + 1;

                pq.offer(new Node(nowVertex + 1, dist[nowVertex + 1]));
            }

            // X-1 위치로 이동
            if (nowVertex - 1 >= 0 && (dist[nowVertex - 1] > dist[nowVertex] + 1)) {
                dist[nowVertex - 1] = dist[nowVertex] + 1;

                pq.offer(new Node(nowVertex - 1, dist[nowVertex - 1]));
            }

            // 2*X 위치로 이동
            if (nowVertex * 2 < MAX && dist[nowVertex * 2] > dist[nowVertex]) {
                dist[nowVertex * 2] = dist[nowVertex];

                pq.offer(new Node(nowVertex * 2, dist[nowVertex * 2]));
            }
        }
        System.out.println(dist[K]);
    }
}
