package Baekjoon.TwoPointers;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * ListOfUniqueNumbers / 골드4 / 1시간 / 24.01.26
 */

public class BJ_13144_ListOfUniqueNumbers {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] input = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        long result = 0;
        int[] cnt = new int[100001];
        int L = 0, R = 0;
        while (L < N) {
            while (R < N && cnt[input[R]] == 0) {
                cnt[input[R]]++;
                R++;
            }
            result += (R - L);

            cnt[input[L]]--;
            L++;
        }

        System.out.println(result);

    }
}
