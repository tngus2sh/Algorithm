package Baekjoon.Greedy;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 회의실배정 / 실버1 / 30분 / 24.02.01
 */

public class BJ_1931_회의실배정 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        ArrayList<int[]> time = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            time.add(new int[]{start, end});
        }

        Collections.sort(time, (o1, o2) -> {
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });

        int lastEnd = -1;
        long answer = 0;
        for (int[] now : time) {
            if (lastEnd <= now[0]) {
                lastEnd = now[1];
                answer++;
            }
        }

        System.out.println(answer);
    }

}