package Baekjoon.UnionFind;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 집합의표현 / 골드5 / 12분 / 24.01.19
 */

public class BJ_1717_집합의표현 {

    static int[] parent;

    public static boolean union (int x, int y) {
        x = find(x);
        y = find(y);

        if (x==y) return false;

        if (x < y) parent[y] = x;
        else parent[x] = y;
        return true;
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        else return find(parent[x]);
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i < n+1; i++) parent[i] = i;

        for (int i =0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 합집합 연산
            if (operation == 0) {
                if (a == b) continue;
                union(a,b);
            }
            // 집합 포함 여부
            else {
                if (a == b) {
                    bw.write("YES" + "\n");
                    continue;
                }
                if (find(a) != find(b)) bw.write("NO" + "\n");
                else bw.write("YES" + "\n");
            }
        }

        bw.flush();

    }
}
