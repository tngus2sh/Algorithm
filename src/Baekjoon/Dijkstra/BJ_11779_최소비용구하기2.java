package Baekjoon.Dijkstra;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 최소비용구하기2 / 골드3 /  / 24.03.12
 */

public class BJ_11779_최소비용구하기2 {

    private static class Node implements Comparable<Node> {
        private int idx;
        private int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    private static ArrayList<Node>[] graph;
    private static int[] seq;
    private static int minCost;

    private static void findRoad(int n, int start, int end) {
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (dist[now.idx] < now.cost) continue;

            if (now.idx == end) {
                minCost = dist[now.idx];
                return;
            }

            for (Node next : graph[now.idx]) {
                if (dist[next.idx] > dist[now.idx] + next.cost) {
                    dist[next.idx] = dist[now.idx] + next.cost;

                    seq[next.idx] = now.idx;
                    queue.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // 도시의 개수
        int m = Integer.parseInt(br.readLine()); // 버스의 개수

        graph = new ArrayList[n+1];
        for (int i = 0; i < n+1; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        seq = new int[n+1];

        findRoad(n, start, end);

        Stack<Integer> stack = new Stack<>();
        int before = end;
        int city = 0;
        while (before != start) {
            city++;
            stack.push(before);
            before = seq[before];
        }
        city++; // 마지막 start까지 개수 카운트

        StringBuilder sb = new StringBuilder();

        sb.append(start).append(" ");
        while (!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(minCost);
        System.out.println(city);
        System.out.println(sb);


    }
}
