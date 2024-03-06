package Baekjoon.TopologicalSort;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * ACMCraft / 골드3 / 2시간 / 24.03.06
 */

public class BJ_1005_ACMCraft {

    static int[] buildingTime, edgeCount;
    static ArrayList<Integer>[] graph;

    private static int sort(int goal, int n) {
        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[n + 1];

        for (int i = 1; i < n+1; i++) {
            result[i] = buildingTime[i];

            if (edgeCount[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int next : graph[node]) {
                result[next] = Math.max(result[next], result[node] + buildingTime[next]);
                edgeCount[next]--;

                if (edgeCount[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return result[goal];
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            edgeCount = new int[n + 1];
            buildingTime = new int[n+1];
            graph = new ArrayList[n+1];
            for (int i = 0; i < n+1; i++) graph[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < n+1; i++) {
                buildingTime[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph[x].add(y);
                edgeCount[y]++;
            }

            int goal = Integer.parseInt(br.readLine());

            int answer = sort(goal, n);

            sb.append(answer).append("\n");

        }

        System.out.print(sb);
    }
}