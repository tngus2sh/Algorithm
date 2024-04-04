package Baekjoon.BackTracking;

import java.io.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 애너그램 / 골드5 / 40분 / 24.04.04
 */

public class BJ_6443_애너그램 {
    static int[] alph;
    static int[] sequence;
    static boolean[] isSelected;
    static int length;
    static StringBuilder sb;

    private static void print() {

        for (int i = 0; i < length; i++) {
            sb.append((char)((sequence[i] + 49) + '0'));
        }

        sb.append("\n");
    }

    private static void gram(int cnt) {

        if (cnt == length) {
            print();
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (alph[i] == 0) continue;

            sequence[cnt] = i;
            alph[i]--;
            gram(cnt+1);
            alph[i]++;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        sb = new StringBuilder();

        for (int i =0; i < N; i++) {

            String input = br.readLine();

            length = input.length();
            alph = new int[26];

            for (int j = 0; j < length; j++) {
                int idx = input.charAt(j) - '0' - 49;
                alph[idx]++;
            }

            sequence = new int[length];
            isSelected  = new boolean[length];

            gram(0);
        }

        System.out.print(sb);
    }
}
