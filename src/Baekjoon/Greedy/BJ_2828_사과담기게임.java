package Baekjoon.Greedy;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 사과담기게임 / 실버5 / 17분 / 24.01.13
 */

public class BJ_2828_사과담기게임 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N =Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int j = Integer.parseInt(br.readLine());

        // 초기 바구니 상태
        int left = 1;
        int right = M;

        // 이동거리
        int dis = 0;

        for (int i =0; i < j; i++) {

            int position = Integer.parseInt(br.readLine());

            // position이 오른쪽에 있으면 오른쪽으로 이동
            if (right < position) {
                dis += position - right;
                left += position - right;
                right = position;
            }
            // position이 왼쪽에 있으면 왼쪽으로 이동
            if (left > position) {
                dis += left - position;
                right -= left - position;
                left = position;
            }
            // 위치가 그 사이에 있다면 가만히 있음

        }

        System.out.println(dis);
    }
}