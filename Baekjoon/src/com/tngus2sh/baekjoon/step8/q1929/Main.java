package com.tngus2sh.baekjoon.step8.q1929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static boolean[] prime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        prime = new boolean[N+1];

        makePrime();

        for(int i = M; i <= N; i++) {
            if(!prime[i]) {
                System.out.println(i);
            }
        }
    }

    public static void makePrime() {
        prime[0] = true;
        prime[1] = true;

        for(int i = 2; i < Math.sqrt(prime.length); i++) {
            for(int j = i*i; j < prime.length; j+=i) {
                prime[j] = true;
            }
        }
    }
}
