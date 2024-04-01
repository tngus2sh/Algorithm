package Baekjoon.BackTracking;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 줄어드는수 / 골드5 / 2시간 / 24.04.01
 */

public class BJ_1174_줄어드는수 {

    static final int[] arr = {9,8,7,6,5,4,3,2,1,0};
    static int N;
    static List<Long> list;

    private static void dfs(long num , int index) {
        if(!list.contains(num)) {
            list.add(num);
        }

        if (index >= 10) {
            return;
        }

        dfs((num*10) + arr[index], index+1);
        dfs(num, index+1);

    }
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();

        dfs(0,0);

        Collections.sort(list);

        if (list.size() < N) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(N-1));
        }

    }
}
