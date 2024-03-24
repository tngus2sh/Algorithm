package Baekjoon;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 물통 / 골드2 /  / 24.02.06
 */

public class BJ_14867_물통 {
    static class Bottle {
        int a;
        int b;
        int cnt;
        public Bottle(int a, int b, int cnt) {
            this.a = a;
            this.b = b;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Bottle{");
            sb.append("a=").append(a);
            sb.append(", b=").append(b);
            sb.append(", cnt=").append(cnt);
            sb.append('}');
            return sb.toString();
        }
    }

    public static void bfs(int a, int b, int aGoal, int bGoal) {
        int[][] visited = new int[a+1][b+1];

        Queue<Bottle> queue = new LinkedList<>();
        queue.offer(new Bottle(0, 0, 0));

        while(!queue.isEmpty()) {
            Bottle now = queue.poll();
            System.out.println(now.toString());

            if (now.a == aGoal && now.b == bGoal) {
                System.out.println(now.cnt);
                return;
            }

            // 1. 물통에 물을 꽉 채운다.
            if (now.a < a && visited[a][now.b] == 0) {
                queue.offer(new Bottle(a, now.b, now.cnt + 1));
                visited[a][now.b] = now.cnt + 1;
            }
            if (now.b < b && visited[now.a][b] == 0) {
                queue.offer(new Bottle(now.a, b, now.cnt + 1));
                visited[now.a][b] = now.cnt +1;
            }
            // 2. 물통의 물을 다 버린다.
            if (now.a != 0 && visited[0][now.b] == 0 ) {
                queue.offer(new Bottle(0, now.b, now.cnt + 1));
                visited[0][now.b] = now.cnt + 1;
            }
            if (now.b != 0 && visited[now.a][b] == 0) {
                queue.offer(new Bottle(now.a, 0, now.cnt +1));
                visited[now.a][b] = now.cnt + 1;
            }
            // 3. 물통 x의 물을 물통 y에 붓는다.
            if (now.a != 0 && now.a <= b - now.b && visited[0][now.a+now.b] == 0) {
                queue.offer(new Bottle(0, now.a + now.b, now.cnt + 1));
                visited[0][now.a+now.b] = now.cnt + 1;
            }
            else if (now.b != b && now.a > b - now.b && visited[now.a-(b-now.b)][b] == 0){
                queue.offer(new Bottle(now.a - (b - now.b), b, now.cnt + 1));
                visited[now.a-(b-now.b)][b] = now.cnt + 1;
            }
            if (now.b != 0 && now.b <= a - now.a && visited[now.a + now.b][0] == 0) {
                queue.offer(new Bottle(now.a + now.b, 0, now.cnt + 1));
                visited[now.a + now.b][0] = now.cnt + 1;
            }
            else if (now.a != a && now.b > a - now.a && visited[a][now.b-(a-now.a)] == 0) {
                queue.offer(new Bottle(a, now.b - (a - now.a), now.cnt + 1));
                visited[a][now.b-(a-now.a)] = now.cnt + 1;
            }
        }

        System.out.println(-1);
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int aGoal = Integer.parseInt(st.nextToken());
        int bGoal = Integer.parseInt(st.nextToken());

        bfs(a, b, aGoal, bGoal);

    }
}
