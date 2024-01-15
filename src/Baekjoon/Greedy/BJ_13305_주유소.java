package Baekjoon.Greedy;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 주유소 / 실버3 / 55분 / 24.01.15
 */

public class BJ_13305_주유소 {

    public static class Liter implements Comparable<Liter> {
        int index;
        int volume;
        public Liter(int index, int volume) {
            this.index = index;
            this.volume = volume;
        }
        @Override
        public int compareTo(Liter o) {
            return this.volume - o.volume;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Liter{");
            sb.append("index=").append(index);
            sb.append(", volume=").append(volume);
            sb.append('}');
            return sb.toString();
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] dis = new int[N-1];
        st = new StringTokenizer(br.readLine());
        for (int i =0; i < N-1; i++) {
            dis[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<Liter> liters = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liters.offer(new Liter(i, Integer.parseInt(st.nextToken())));
        }

        long result = 0;
        boolean[] visited = new boolean[N];
        int beforeIdx = N-1;

        while(!liters.isEmpty()) {
            Liter liter = liters.poll();

            // 이미 이전에 방문했던 index라면 패쓰
            if (visited[liter.index]) continue;

            // 마지막 인덱스라면 패쓰
            if (liter.index == N-1) continue;

            int disSum = 0;
            for (int i = liter.index; i < beforeIdx; i++) {
                visited[i] = true;
                disSum += dis[i];
            }

            beforeIdx = liter.index;

            result += ((long)disSum * (long)liter.volume);
        }

        System.out.println(result);
    }
}
