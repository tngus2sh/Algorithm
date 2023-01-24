package com.tngus2sh.baekjoon.step7.q1193;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        int sum = 0;
        int index = 1;
        String result = null;
        while(true) {
            sum += index;

            if(sum >= num) {
                for(int i = 1; i <= (index + 1); i++) {
                    if((sum - index + i) == num) {
                        if(index%2 == 0) {
                            result = i + "/" + (index + 1 - i);
                        } else {
                            result = (index + 1-i) + "/" + i;
                        }
                        break;
                    }
                }
                System.out.println(result);
                break;
            }
            index++;
        }
    }
}
