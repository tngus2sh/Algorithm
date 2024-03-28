package Baekjoon.BruteForce;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 하늘에서별똥별이빗발친다 / 골드3 /  / 24.03.28
 */

public class BJ_14658_하늘에서별똥별이빗발친다 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 별똥별이 떨어지는 가로 길이
        int M = Integer.parseInt(st.nextToken()); // 별똥별이 떨어지는 세로 길이

        int L = Integer.parseInt(st.nextToken()); // 트램펄린의 한 변의 길이
        int K = Integer.parseInt(st.nextToken()); // 별똥별의 개수

        List<int[]> stars = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            stars.add(new int[] {y, x});
        }

        int res = 0;

        for (int[] s1 : stars) {
            for (int[] s2 : stars) {
                int cnt = 0;
                for (int[] s3 : stars) {
                    if (s1[0] <= s3[0] && s3[0] <= (s1[0] + L) && s2[1] <= s3[1] && s3[1] <= (s2[1] + L)) cnt++;
                }
                res = Math.max(res, cnt);
            }
        }

        System.out.println((K - res));

    }
}