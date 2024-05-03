package Baekjoon.BackTracking;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 가르침 / 골드4 / 30분 / 24.05.03
 */

public class BJ_1062_가르침 {

    private static String[] input;
    private static boolean[] visited;
    private static int N, K, answer;

    private static void cal() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 4; j < input[i].length() - 4; j++) {
                int idx = input[i].charAt(j) - '0' - 49;

                if (!visited[idx]) {
                    sum++;
                    break;
                }
            }
        }

        answer = Math.max(answer, N - sum);
    }

    private static void recur(int cnt, int start) {
        if (cnt == K-5) {
            cal();
            return;
        }

        for (int i = start; i < 26; i++) {
            if (i == 0 || i == 2 || i == 8 || i == 13 || i == 19) continue;
            if (visited[i]) continue;

            visited[i] = true;
            recur(cnt+1, i);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 단어의 개수
        K = Integer.parseInt(st.nextToken()); // 글자 수

        // 5개보다 적어지게 되면 a, n, t, i, c 가 포함이 될 수 없다.
        if (K < 5) {
            System.out.println(0);
            return;
        }

        visited = new boolean[26];

        // a, n , t, i, c >> check
        visited[('a' - '0' - 49)] = true;
        visited[('c' - '0' - 49)] = true;
        visited[('i' - '0' - 49)] = true;
        visited[('n' - '0' - 49)] = true;
        visited[('t' - '0' - 49)] = true;

        input = new String[N];

        for (int i = 0; i < N; i++) {
            input[i] = br.readLine();
        }

        recur(0, 0);

        System.out.println(answer);

    }
}
