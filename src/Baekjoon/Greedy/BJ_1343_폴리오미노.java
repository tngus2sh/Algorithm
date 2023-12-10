package Baekjoon.Greedy;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 폴리오미노 / 실버5 / 21:40 / 231210
 */
public class BJ_1343_폴리오미노 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringBuilder sb = new StringBuilder();

        int xNum = 0;
        for (int i =0; i < input.length(); i++) {
            if (input.charAt(i) == '.' ) {
                sb.append(".");
            } else {
                xNum++;
                char next = 'X';
                if (i < input.length()-1) next = input.charAt(i+1);
                else next = 'N';

                if (next == '.' || next == 'N') {
                    // 1. 홀수라면 -1 출력
                    if (xNum % 2 != 0) {
                        System.out.println("-1");
                        return;
                    }
                    // 2. 4로 나누어 떨어지는 만큼 AAAA로 대치
                    int A = xNum / 4;
                    int B = (xNum % 4)/2;
                    sb.append("AAAA".repeat(A));
                    sb.append("BB".repeat(B));
                    // xNum 초기화
                    xNum = 0;
                }
            }
        }

        System.out.println(sb.toString());
    }
}