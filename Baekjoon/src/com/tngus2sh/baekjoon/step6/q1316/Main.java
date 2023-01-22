package com.tngus2sh.baekjoon.step6.q1316;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder prev = null;

        int N = Integer.parseInt(buf.readLine());
        String s;
        int group = 0;
        boolean isGroup;

        for(int n = 0; n < N; n++) {
            s = buf.readLine();
            prev = new StringBuilder(String.valueOf(s.charAt(0)));
            isGroup = true;
            for(int i = 1; i < s.length(); i++) {
                if(s.charAt(i) == s.charAt(i-1)) {
                    continue;
                }
                if(prev.toString().contains(String.valueOf(s.charAt(i))) && (prev.length() > 1)) {
                    isGroup = false;
                    break;
                }
                prev.append(String.valueOf(s.charAt(i)));
            }
            if(isGroup) {
                group++;
            }
        }
        System.out.println(group);
    }
}
