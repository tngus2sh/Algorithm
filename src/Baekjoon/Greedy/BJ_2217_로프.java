package Baekjoon.Greedy;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 로프 / 실버4 / 24분 / 24.01.13
 */

public class BJ_2217_로프 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] rope = new int[10001];
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> {
            return o2-o1;
        });

        for (int i =0; i < N; i++) {
            int w = Integer.parseInt(br.readLine());
            rope[w]++;

            if (!pq.contains(w)) pq.offer(w);
        }

        int val = 0;
        int result = 0;
        while(!pq.isEmpty()) {
            int index = pq.poll();

            val += rope[index];

            result = Math.max(result, index * val);
        }

        System.out.println(result);
    }
}
