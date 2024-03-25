package Baekjoon.BitMasking;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 집합 / 실버5 / 20분 / 24.03.25
 */

public class BJ_11723_집합 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        int m = Integer.parseInt(br.readLine());

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if (cmd.equals("add")) {
                int num = Integer.parseInt(st.nextToken());
                set.add(num);
            }
            else if (cmd.equals("remove")) {
                int num = Integer.parseInt(st.nextToken());
                set.remove(num);
            }
            else if (cmd.equals("check")) {
                int num = Integer.parseInt(st.nextToken());

                if (set.contains(num)) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            }
            else if (cmd.equals("toggle")) {
                int num = Integer.parseInt(st.nextToken());

                if (set.contains(num)) set.remove(num);
                else set.add(num);
            }
            else if (cmd.equals("all")) {
                set.clear();
                for (int j = 1; j <= 20; j++) {
                    set.add(j);
                }
            }
            else if (cmd.equals("empty")) {
                set.clear();
            }
        }


        System.out.print(sb);
    }
}
