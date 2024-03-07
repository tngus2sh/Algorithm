package Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 소수의연속합 / 골드3 / 20분 / 23.03.07
 */

public class BJ_1644_소수의연속합 {

    static ArrayList<Integer> primeNumber;

    public static void prime(int n) {
        boolean[] isPrime = new boolean[n + 1];

        isPrime[0] = isPrime[1] = true;
        for (int i = 2; i*i < n+1; i++) {
            for (int j = i*i; j < n+1; j+=i) isPrime[j] = true;
        }

        for (int i = 0; i < isPrime.length; i++) {
            if (!isPrime[i]) primeNumber.add(i);
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        primeNumber = new ArrayList<>();

        prime(n);

        int length = primeNumber.size();
        int L = 0;
        int R = 0;
        int total = 0;
        int answer = 0;

        while(R < length) {
            total += primeNumber.get(R++);
            while (L < length && total > n) {
                total -= primeNumber.get(L++);
            }

            if (total == n) answer++;
        }

        System.out.println(answer);

    }
}
