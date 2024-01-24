package Baekjoon.DFS;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 텀프로젝트 / 골드3 / 1시간 2분 / 24.01.24
 */

public class BJ_9466_텀프로젝트 {

    static int[] group;
    static boolean[] visited;
    static boolean[] check;
    static int student;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            group = new int[n + 1];
            visited = new boolean[n+1];
            check = new boolean[n+1];
            student = 0;
            for (int i = 1; i <= n; i++) {
                group[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i < group.length; i++) {
                if (check[i]) continue;

                dfs(i);
            }

            bw.write((n - student) + "\n");

        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int now) {
        if (visited[now]) {
            check[now] = true;
            student++;
        }
        else {
            visited[now] = true;
        }

        if (!check[group[now]]) {
            dfs(group[now]);
        }

        visited[now] = false;
        check[now] = true;
    }
}