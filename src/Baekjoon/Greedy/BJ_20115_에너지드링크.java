package Baekjoon.Greedy;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 에너지드링크 / 실버3 / 48분 / 24.01.25
 */

public class BJ_20115_에너지드링크 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] drink = new int[N];
        for (int i = 0; i < N; i++) {
            drink[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(drink);

        double result = 0;
        for (int i = 0; i < drink.length-1; i++) {
            result += (double)drink[i]/2;
        }
        result += drink[drink.length - 1];


        System.out.println(result);
    }
}
