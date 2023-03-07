import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1783_병든나이트 {
    static final int[][] directions = {{-2,1}, {-1,2}, {1,2}, {2,1}};
    static int N,M;
    static boolean[][] visited;
    static int maxDis;
    static int[] selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 세로 : N, 가로 : M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 칸 방문여부 체크
        visited = new boolean[N][M];
        selected = new int[4];

        dfs(N-1, 0, 0, 0);

        System.out.println(maxDis);
    }

    static void dfs(int x, int y, int dis, int index) {
        selected[index]--;
        if(x < 0 || x >= N || y < 0 || y >= M) {
            if (dis >= 4) {
                for (int i = 0; i < 4; i++) {
                    if(selected[i]==0) return;
                }
            }
            maxDis = Math.max(maxDis, dis);
            return;
        }
        if (visited[x][y]) return;
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int dx = x + directions[i][0];
            int dy = y + directions[i][1];

            selected[i]++;
            dfs(dx, dy, dis+1, i);
        }
    }
}
