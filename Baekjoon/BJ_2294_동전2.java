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
        int[] coins = new int[k+1];

        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());

            int mod = k / coin;
            // -- debug
//            System.out.println(mod);
            for (int j = 1; j <= mod; j++) {
                // 처음에 들어온거라면 그대로 깂 넣어줌
                if (i == 0) {
                    coins[coin * j] = j;
                    continue;
                }

                if(coins[k-(coin*j)] > 0){
                    int coinCnt = j + coins[k - (coin * j)];


                    // 기존에 있던 코인 개수보다 작으면 값 갱신
                    if (coins[coin * j] > 0 && j < coins[coin * j]) coins[coin * j] = j;
                    if (coins[k] > 0 && coinCnt < coins[k]) coins[k] = coinCnt;
                }

            }
        }

        // -- debug
//        System.out.println(Arrays.toString(coins));
        if(coins[k])

        // 최소 동전 개수 찾기
        System.out.println(coins[k]);
    }
}
