package Baekjoon.Greedy;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 강의실배정 / 골드5 / 45분 / 24.04.30
 */

public class BJ_11000_강의실배정 {

    private static class Lecture {
        int start;
        int end;

        Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        Lecture[] lectures = new Lecture[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            lectures[i] = new Lecture(s, t);
        }

        Arrays.sort(lectures, (o1, o2) -> {
            if (o1.start == o2.start) return o1.end - o2.end;
            return o1.start - o2.start;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0].end);

        for (int i = 1; i < lectures.length; i++) {
            if (pq.peek() <= lectures[i].start) {
                pq.poll();
            }
            pq.offer(lectures[i].end);
        }

        System.out.println(pq.size());
    }
}
