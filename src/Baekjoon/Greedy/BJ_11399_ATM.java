package Baekjoon.Greedy;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * ATM / 실버4 / 9분 / 24.01.25
 */

public class BJ_11399_ATM {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] people = new int[N];
        for (int i = 0; i < N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(people);

        int result = 0;
        int sum = 0;
        for (int i = 0; i < people.length; i++) {
            sum += people[i];
            result += sum;
        }

        System.out.println(result);

    }
}