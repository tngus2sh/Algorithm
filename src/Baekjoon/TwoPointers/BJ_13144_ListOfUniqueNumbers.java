package Baekjoon.TwoPointers;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * ListOfUniqueNumbers / 골드4 /  / 24.01.26
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

        long result =0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < input.length; i++) {
            set.add(input[i]);
            int bIdx = i+1;

            while (bIdx < input.length && !set.contains(input[bIdx])) {
                set.add(input[bIdx]);
                bIdx++;
            }

            result += bIdx - i;

            set.clear();
        }

        System.out.println(result);

    }
}
