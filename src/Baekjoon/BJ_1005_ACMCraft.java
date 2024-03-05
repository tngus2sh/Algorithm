package Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * ACMCraft / 골드3 /  / 24.03.02
 */

public class BJ_1005_ACMCraft {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            int[] edgeCount = new int[n + 1];
            int[] buildTime = new int[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < n + 1; i++) {
                buildTime[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph.get(x).add(y);
                edgeCount[y]++;
            }

            int goal = Integer.parseInt(br.readLine());

            Queue<int[]> queue = new LinkedList<>();

            int[] depth = new int[n+1];
            int nowDepth = 0;
            int time = 0;

            for (int i = 1; i < n + 1; i++) {
                if (edgeCount[i] == 0) {
                    queue.offer(new int[] {i, 0});
                    depth[0] = Math.max(depth[0], buildTime[i]);
                }
            }


            while (!queue.isEmpty()) {
                int[] now = queue.poll();

                for (int next : graph.get(now[0])) {
                    edgeCount[next]--;

                    if (edgeCount[next] == 0) {
                        if (next == goal) {
                            nowDepth = now[1];
                            break;
                        }

                        queue.offer(new int[] {next, now[1] + 1});
                        depth[now[1]+1] = Math.max(depth[now[1]+1], buildTime[next]);
                    }
                }
            }

            for (int i = 0; i < nowDepth + 1; i++) {
                time += depth[i];
            }
            time += buildTime[goal];

            sb.append(time).append("\n");
        }

        System.out.print(sb);
    }
}