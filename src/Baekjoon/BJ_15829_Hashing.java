package Baekjoon;

import java.io.*;
import java.math.BigInteger;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * Hashing /  /  /
 */

public class BJ_15829_Hashing {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());

        String str = br.readLine();

        int r = 31;
        int M = 1234567891;
        BigInteger answer = new BigInteger("0");
        for (int i = 0; i < str.length(); i++) {
            int a = str.charAt(i) - 96;
            answer = answer.add(BigInteger.valueOf(a).multiply(BigInteger.valueOf(r).pow(i)));
        }

        System.out.println(answer.mod(BigInteger.valueOf(M)));

    }
}
