package Baekjoon.BinarySearch;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 공유기설치 / 골드4 / 2시간 / 24.02.17
 */

public class BJ_2110_공유기설치 {

    static int[] graph;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        graph = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(graph);

        int lo = 1;
        int hi = graph[n-1] - graph[0] + 1;

        while (lo < hi) {
            int mid = (hi + lo)/2;

            if (canInstall(mid) < c) {
                hi = mid;
            }
            else {
                lo = mid + 1;
            }
        }

        System.out.println((lo - 1));
    }

    public static int canInstall(int distance) {
        int count = 1;
        int lastLocate = graph[0];

        for (int i = 1; i < graph.length; i++) {
            int locate = graph[i];

            if (locate - lastLocate >= distance) {
                count++;
                lastLocate = locate;
            }
        }
        return count;
    }
}
