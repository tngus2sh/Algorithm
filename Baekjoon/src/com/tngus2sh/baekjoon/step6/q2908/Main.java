package com.tngus2sh.baekjoon.step6.q2908;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String aS = sc.next();
        String bS = sc.next();

        StringBuffer buf = new StringBuffer();

        int A, B;

        for(int i = (aS.length()-1); i >= 0;i--) {
            buf.append(aS.charAt(i));
        }
        A = Integer.parseInt(String.valueOf(buf));
        buf.delete(0, buf.length());

        for(int i = (bS.length()-1); i >= 0;i--) {
            buf.append(bS.charAt(i));
        }
        B = Integer.parseInt(String.valueOf(buf));

        if (A < B) {
            System.out.println(B);
            return;
        }
        System.out.println(A);
    }
}
