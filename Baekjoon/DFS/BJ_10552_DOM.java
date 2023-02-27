package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * DOM / 실버 2 / 30분
 * https://www.acmicpc.net/problem/10552
 */
public class BJ_10552_DOM {

    static HashMap<Integer, LinkedList<Integer>> adjacent;
    static boolean[] visited;
    static int M, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N : 사람 수
        int N = Integer.parseInt(st.nextToken());
        // M : 채널 수(1~M)
        M = Integer.parseInt(st.nextToken());
        // p : 처음 채널
        int p = Integer.parseInt(st.nextToken());

        // 관게를 그려주는 인접리스트
        adjacent = new HashMap<>();

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            // 선호 채널
            int a = Integer.parseInt(st.nextToken());
            // 비선호 채널
            int b = Integer.parseInt(st.nextToken());

            // 기존에 key가 존재하지 않았다면 새로 생성하고 값을 넣어준다.
            if(!adjacent.containsKey(b)) {
                adjacent.put(b, new LinkedList<>());
            }
            adjacent.get(b).add(a);
        }

        // 방문체크 초기화
        visited = new boolean[M+1];

        dfs(p, 0);

        System.out.println(result);
    }

    public static void dfs(int key, int cnt) {
        if(visited[key]) {
            System.out.println(-1);
            System.exit(0);
        }
        visited[key] = true;


        if(adjacent.containsKey(key)) {
            for (int i = 0; i < adjacent.get(key).size(); i++) {
                if (!visited[i]) {
                    dfs(adjacent.get(key).get(i), cnt+1);
                }
            }
        }
        result = Math.max(result, cnt);
    }
}
