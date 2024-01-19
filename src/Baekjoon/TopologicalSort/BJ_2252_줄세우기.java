package Baekjoon.TopologicalSort;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 줄세우기 / 골드3 / 15분 / 24.01.19
 */

public class BJ_2252_줄세우기 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] edgeCount = new int[N + 1];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < edgeCount.length; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            edgeCount[b]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i =1; i < edgeCount.length; i++) {
            if (edgeCount[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()) {
            int nodeNo = queue.poll();

            bw.write(nodeNo + " ");

            for (int nextNode : graph.get(nodeNo)) {
                edgeCount[nextNode]--;

                if (edgeCount[nextNode] == 0) queue.offer(nextNode);
            }

        }

        bw.flush();

    }
}
