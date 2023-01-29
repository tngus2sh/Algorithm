package com.tngus2sh.baekjoon.step8.q2581;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrimeMain {
    /**
     * boolean[] prime
     * - false : 소수
     * - true : 소수x
     */
    public static boolean[] prime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int sum = 0;
        int min = N+1;

        prime = new boolean[N+1]; // 0~N

        makePrime();

        for(int i = M; i <= N; i++) {
            if(!prime[i]) { // 소수일 때
                sum += i;
                if(min == N+1){
                    min = i;
                }
            }
        }

        if(sum == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sum);
            System.out.println(min);
        }
    }

    /**
     * 에라토스테네스 체 알고리즘
     */
    public static void makePrime() {

        // 0하고 1은 소수에서 제외
        prime[0] = true;
        prime[1] = true;

        for(int i = 2; i < Math.sqrt(prime.length); i++) {
            for(int j = i*i; j < prime.length; j+=i) {
                prime[j] = true;
            }
        }
    }
}
