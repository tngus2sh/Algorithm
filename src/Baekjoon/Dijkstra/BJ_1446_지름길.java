package Baekjoon.Dijkstra;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 지름길 / 실버1 / 40분 / 231211
 */

public class BJ_1446_지름길 {

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

        // 지름길의 개수 : N, 고속도로의 길이 : D
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] graph = new ArrayList[10001];

        for (int i =0; i < 10001; i++) {
            graph[i] = new ArrayList<>();
        }

        ArrayList<Integer> endList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, dis));

            if (!endList.contains(start)) endList.add(start);
            if (!endList.contains(end)) endList.add(end);
        }
        if (!endList.contains(D)) endList.add(D);

        Collections.sort(endList);

        // 중간 경유 지점에 해당하는 곳들 간선 추가
        int before = 0;
        for (int now : endList) {
            graph[before].add(new Node(now, now-before));
            before = now;
        }
        graph[0].add(new Node(D, D));

        boolean[] check = new boolean[10001];
        int[] dist = new int[10001];
        int INF = Integer.MAX_VALUE;

        Arrays.fill(dist, INF);
        dist[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        // 최단 거리 구하기
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

        System.out.println(dist[D]);
    }
}
