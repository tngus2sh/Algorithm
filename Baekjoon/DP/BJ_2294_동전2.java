import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2294_동전2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // n : 동전의 종류 , k : 가치의 합
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 동전 배열
        int[] coins = new int[n];

        int[] dp = new int[k+1];

        for (int i = 0; i < k+1; i++) {
            dp[i] = 100001;
        }

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < k+1; j++) {
                if(j >= coins[i]) {
                    dp[j] = dp[j] + dp[j-coins[i]];
                }
            }
        }


        // 최소 동전 개수 찾기
        System.out.println(dp[k]);
    }
}
