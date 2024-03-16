package Baekjoon.Structure;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * AC / 골드5 / 30분 / 24.03.16
 */

public class BJ_5430_AC {

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String P = br.readLine();

            int n = Integer.parseInt(br.readLine());

            String input = br.readLine();

            String[] inputArr = input.substring(1, input.length()-1).split(",");

            Deque<Integer> list = new LinkedList<>();
            boolean isFirstOrder = true;

            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(inputArr[i]));
            }

            boolean isError = false;

            for (int i = 0; i < P.length(); i++) {
                char cmd = P.charAt(i);

                if (list.isEmpty() && cmd == 'D') {
                    sb.append("error").append("\n");
                    isError = true;
                    break;
                }

                if (cmd == 'R') {
                    isFirstOrder = !isFirstOrder;
                }
                else if (cmd == 'D') {
                    if (isFirstOrder) {
                        list.pollFirst();
                    }
                    else {
                        list.pollLast();
                    }
                }
            }

            if (!isError) {
                sb.append("[");
                while (!list.isEmpty()) {
                    if (isFirstOrder) {
                        sb.append(list.pollFirst());
                    } else {
                        sb.append(list.pollLast());
                    }

                    if (!list.isEmpty()) {
                        sb.append(",");
                    }
                }
                sb.append("]").append("\n");
            }
        }

        System.out.print(sb);
        
    }
}