package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * 수 정렬하기3 / 브론즈1 / 15분
 */

public class q10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for(int i = 0 ; i < N ; i++) {
            arr[i]  = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        for(int i = 0 ;i < N; i++) {
            sb.append(arr[i]).append("\n");
        }

        System.out.println(sb);
    }
}
