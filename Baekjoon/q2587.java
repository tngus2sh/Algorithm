import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 대표값 / 브론즈2 / 15분
 * https://www.acmicpc.net/problem/2587
 */
public class q2587 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int avg = 0;
        int[] mean = new int[5];
        for(int i = 0; i < 5; i++) {
            int num = Integer.parseInt(br.readLine());
            avg += num;
            mean[i] = num;
        }

        int[] idx = new int[5];
        Arrays.sort(mean);
        avg /= 5;
        System.out.println(avg);
        System.out.println(mean[2]);
    }
}

