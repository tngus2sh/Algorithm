package Baekjoon.Tree;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 구간합구하기 / 골드1 / 30분 / 24.02.04
 */

public class BJ_2042_구간합구하기 {

    public static class SegmentTree {
        private long[] tree;
        SegmentTree(int n) {
            double treeHeight = Math.ceil(Math.log(n)/Math.log(2))+1;
            long treeNodeCount = Math.round(Math.pow(2, treeHeight));
            tree = new long[Math.toIntExact(treeNodeCount)];
        }

        long init(long[] arr, int node, int start, int end) {
            if (start == end) {
                return tree[node] = arr[start];
            }
            else {
                return tree[node] = init(arr, node*2, start, (start+end)/2)
                        + init(arr, node*2+1, (start+end)/2+1, end);
            }
        }

        long sum(int node, int start, int end, int left, int right) {
            if (left > end || right < start) {
                return 0;
            }
            else if (left <= start && end <= right) {
                return tree[node];
            }
            else {
                return sum(node*2, start, (start+end)/2, left, right)
                        + sum(node*2+1, (start+end)/2+1, end, left, right);
            }
        }

        void update(int node, int start, int end, int index, long diff) {
            if (index < start || end < index) {
                return;
            }
            else {
                tree[node] += diff;

                if (start != end) {
                    update(node * 2, start, (start + end) / 2, index, diff);
                    update(node*2+1, (start+end)/2+1, end, index, diff);
                }
            }

        }

    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long arr[] = new long[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        SegmentTree segmentTree = new SegmentTree(N);
        segmentTree.init(arr, 1, 1, N);

        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                segmentTree.update(1, 1, N, b, c-arr[b]);
                arr[b] = c;
            }
            else {
                sb.append(segmentTree.sum(1, 1, N,b,(int)c)).append("\n");
            }
        }

        System.out.print(sb.toString());


    }
}
