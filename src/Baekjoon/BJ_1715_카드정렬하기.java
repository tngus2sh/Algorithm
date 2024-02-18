package Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 카드정렬하기 / 골드4 / 58분 / 24.02.18
 */

public class BJ_1715_카드정렬하기 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] input = new int[n];

        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(0);
            System.exit(0);
        }

        PriorityQueue<Long> queue = new PriorityQueue<>((o1, o2) -> {
            return Long.compare(o1, o2);
        });

        for (int i = 0; i < input.length; i++) {
            queue.offer((long) input[i]);
        }

        long total = 0;
        while(!queue.isEmpty()) {
            long now = queue.poll();

//            System.out.println("now : " + Arrays.toString(now));
            long sum = 0;

            sum += now;
            total += now;
            if (!queue.isEmpty()) {
                long next = queue.poll();
                sum += next;
                total += next;
            }

            if (!queue.isEmpty()) {
                queue.offer(sum);
            }
        }

        System.out.println(total);
    }
}
