package Baekjoon.Greedy;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 회의실배정 / 실버1 /  / 231211
 */

public class BJ_1931_회의실배정 {

    public static class Meeting implements Comparable<Meeting> {
        int start;
        int end;
        int cost;
        public Meeting(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Meeting o) {
            return Integer.compare(this.end, o.end);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 회의의 수
        int N = Integer.parseInt(br.readLine());

        // 회의 목록 저장하는 배열
        ArrayList<Meeting> meetings = new ArrayList<>();

        for (int i =0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            meetings.add(new Meeting(start, end, 0 ));
        }

        // 시작하는 시간을 기준으로 정렬
        Collections.sort(meetings, Comparator.comparingInt(o -> o.start));

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int now = 0;

        for (Meeting meeting : meetings) {
            int start = meeting.start;
            int end = meeting.end;

            if (map.containsKey(start)) {
                map.put(end, map.get(start) + 1);
                now = Math.max(now, map.get(start));
                res = Math.max(res, map.get(start) + 1);
            } else {
                if (map.containsKey(end)) {
                    int val = Math.max(map.get(end), now+1);
                    map.put(end, val);
                    res = Math.max(res, val);
                } else {
                    map.put(end, now+1);
                    res = Math.max(res, now+1);
                }
            }
        }

        System.out.println(res);
    }
}
