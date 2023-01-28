package com.tngus2sh.baekjoon.step7.q10757;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HandMain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        String sA = st.nextToken();
        String sB = st.nextToken();

        int maxLength = Math.max(sA.length(), sB.length());

        int[] A = new int[maxLength + 1];
        int[] B = new int[maxLength + 1];

        int idx = 0;

        for(int i = sA.length() - 1; i >= 0; i--) {
            A[idx++] = sA.charAt(i) - '0';
        }

        idx = 0;
        for(int i = sB.length() -1; i >= 0; i--) {
            B[idx++] = sB.charAt(i) - '0';
        }

        for(int i = 0; i < maxLength; i++) {
            int value = A[i] + B[i];
            A[i] = value % 10;
            A[i+1] += value / 10;
        }

        if(A[maxLength] != 0) {
            System.out.print(A[maxLength]);
        }
        for(int i = maxLength-1; i >= 0; i--) {
            System.out.print(A[i]);
        }
    }
}
