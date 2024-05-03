package Baekjoon.Greedy;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 배 / 골드5 / 1시간 / 24.05.03
 */

public class BJ_1092_배 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        List<Integer> crane = new ArrayList<>();
        for (int i =0; i < N; i++) {
            crane.add(Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        List<Integer> box = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(crane, (o1, o2) -> {
            return o2 - o1;
        });
        Collections.sort(box, (o1, o2) -> {
            return  o2 - o1;
        });

        if (crane.get(0) < box.get(0)) {
            System.out.println(-1);
            return;
        }

        int time = 0;

        while (!box.isEmpty()) {
            int boxIdx = 0, craneIdx = 0;

            while (craneIdx < N) {
                if (box.size() == boxIdx) {
                    break;
                }
                else if (box.get(boxIdx) <= crane.get(craneIdx)) {
                    box.remove(boxIdx);
                    craneIdx++;
                }
                else {
                    boxIdx++;
                }
            }
            time++;

        }

        System.out.println(time);
    }
}