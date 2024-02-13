package Baekjoon.Structure;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 오큰수 / 골드4 / 2시간 / 24.02.13
 */

public class BJ_17298_오큰수 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int[] input = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && input[stack.peek()] < input[i]) {
                input[stack.pop()] = input[i];
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            input[stack.pop()] = -1;
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            sb.append(input[i]).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}
