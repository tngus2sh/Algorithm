package Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * ㅈㅔ로 /  /  /
 */

public class BJ_10773_ㅈㅔ로 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < k; i++) {
            int input =  Integer.parseInt(br.readLine());

            if (i > 0 && input == 0) {
                stack.pop();
            }
            else {
                stack.push(input);
            }
        }

        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        System.out.println(sum);

    }
}
