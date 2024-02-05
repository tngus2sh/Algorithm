package Baekjoon.Tree;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 수들의 합7 / 골드1 / 1시간 / 24.02.05
 */

public class BJ_2268_수들의합7 {

    static class SegmentTree {
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

        long sum (int node, int start, int end, int left, int right) {
            if (right < start || end < left) {
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
                    update(node*2, start, (start+end)/2, index, diff);
                    update(node*2+1, (start+end)/2+1, end, index, diff);
                }
            }
        }

    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] arr = new long[N + 1];
        Arrays.fill(arr, 0);

        SegmentTree tree =new SegmentTree(N);
        tree.init(arr, 1, 1, N);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (cmd == 0) {
                if (a > b) {
                    int temp = b;
                    b = a;
                    a = temp;
                }
                long answer = tree.sum(1, 1, N, a, b);
                sb.append(answer).append("\n");
            }
            else {
                tree.update(1, 1, N, a, (long)b-arr[a]);
                arr[a] = b;
            }
        }

        System.out.print(sb.toString());

    }
}
