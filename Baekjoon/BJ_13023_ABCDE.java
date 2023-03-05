import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * ABCDE / 골드 5 / 2시간
 * https://www.acmicpc.net/problem/13023
 */
public class BJ_13023_ABCDE {
    static int N, M;
    static HashMap<Integer, LinkedList<Integer>> adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 사람의 수 : N , 친구 관계의 수 : M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 친구 관계 리스트
        adj = new HashMap<>();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 생성되어 있지 않았다면 생성
            if(!adj.containsKey(a)) adj.put(a, new LinkedList<>());
            if(!adj.containsKey(b)) adj.put(b, new LinkedList<>());

            // 친구관계 저장
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        for (int i = 0; i < adj.size(); i++) {
            visited = new boolean[N];
            dfs(0, i);
        }
        System.out.println(0);
    }

    static void dfs(int cnt, int num) {
        if(cnt >= 4){
            System.out.println(1);
            System.exit(0);
            return;
        }

        if(!adj.containsKey(num)) return;

        visited[num] = true;

        for (int next: adj.get(num)) {

            if(visited[next]) continue;

            visited[next] = true;
            dfs(cnt + 1, next);
            visited[next] = false;
        }
    }
}
