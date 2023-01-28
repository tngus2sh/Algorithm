package com.tngus2sh.baekjoon.step7.q10757;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BigintegerMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        BigInteger bigA = new BigInteger(st.nextToken());
        BigInteger bigB = new BigInteger(st.nextToken());

        bigA = bigA.add(bigB);
        System.out.println(bigA);
    }
}
