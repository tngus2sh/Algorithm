package com.tngus2sh.baekjoon.step6.q2941;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        String s = buf.readLine();
        int len = s.length();
        int word = 0;

        for (int i = 0; i < len; i++) {
            if (s.contains("c=")) {
                s = s.replaceFirst("c=", " ");
                word++;
            }
            if (s.contains("c-")) {
                s = s.replaceFirst("c-", " ");
                word++;
            }
            while(s.contains("dz=")) {
                s = s.replaceFirst("dz=", " ");
                word++;
            }
            if (s.contains("d-")) {
                s = s.replaceFirst("d-", " ");
                word++;
            }
            if (s.contains("lj")) {
                s = s.replaceFirst("lj", " ");
                word++;
            }
            if (s.contains("nj")) {
                s = s.replaceFirst("nj", " ");
                word++;
            }
            if (s.contains("s=")) {
                s = s.replaceFirst("s=", " ");
                word++;
            }
            if (s.contains("z=")) {
                s = s.replaceFirst("z=", " ");
                word++;
            }
        }
        s = s.replaceAll(" ", "");
        word += s.length();
        System.out.println(word);
    }
}
