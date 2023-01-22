package com.tngus2sh.baekjoon.step6.q1157;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));

        int ascii = 0;
        // 결과 넣는 배열
        int[] result = new int[26];
        // max 값
        int max = 0;
        // maxIndex
        int maxIndex = 0;
        // 결과 스트링
        char res;

        try {
            String alph = buff.readLine();

            for(int i = 0; i < alph.length();i++){
                ascii = alph.charAt(i);
                if((ascii >= 97) && (ascii <= 122)) {
                    // 영문 소문자인 경우
                    // 대문자로 바꿔줌
                    ascii -= 32;
                }
                result[(ascii-65)%26]++;
            }

            for(int i = 0; i < 26; i++){
                if(max < result[i]) {
                    max = result[i];
                    maxIndex = i;
                }
            }
            maxIndex += 65;
            res = (char)maxIndex;

            int index = 0;
            for(int i = 0; i < 26; i++) {
                if(max == result[i]) {
                    index++;
                    if(index > 1) {
                        res = '?';
                        break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(res);
    }
}
