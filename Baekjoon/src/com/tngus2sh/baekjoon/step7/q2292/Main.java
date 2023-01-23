package com.tngus2sh.baekjoon.step7.q2292;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int room = sc.nextInt();

        int index = 1;
        int sum = 0;

        if(room == 1){
            System.out.println(1);
            return;
        }
        while(true) {
            for(int i = 1; i <= index; i++) {
                sum += i;
            }
            sum *= 6;
            sum++;
            if(room <= sum) {
                break;
            }
            index++;
            sum = 0;
        }

        System.out.println(++index);
    }
}
