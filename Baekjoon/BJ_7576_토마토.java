import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 토마토 / 골드 5 / 30분
 * https://www.acmicpc.net/problem/7576
 */
public class BJ_7576_토마토 {
	static final int[][] directions = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	static int N,M,tomatoNum;
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> queue;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 상자의 세로 칸 수 : N, 가로 칸 수 : M
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		// 상자
		map = new int[N][M];

		visited = new boolean[N][M];
		queue = new LinkedList<>();
		// 토마토 개수
		int tomatoCnt = 0;
		// 토마토
		LinkedList<int[]> tomatos = new LinkedList<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				int val = Integer.parseInt(st.nextToken());
				
				if(val == 0) tomatoCnt++;
				else if(val == 1) {
					tomatoCnt++;
					queue.add(new int[] {n, m, 0});
					visited[n][m] = true;
				}
				map[n][m] = val;
			}
		}
		
		// 처음부터 다 익어있는 상태라면
		if(tomatoCnt == tomatos.size()) {
			System.out.println(0);
			return;
		}
		int day = 0;
		day = bfs();
		// 토마토가 다 익지 못하는 상황
		if(tomatoNum != tomatoCnt) {
			System.out.println(-1);
			return;
		}
		// 최소 날짜 출력
		System.out.println(day);
	}
	
	static int bfs() {
		int day = -1;
		tomatoNum = 0;
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			int x = poll[0];
			int y = poll[1];
			day = poll[2];
			tomatoNum++;
			
			for (int i = 0; i < 4; i++) {
				int dx = x + directions[i][0];
				int dy = y + directions[i][1];
				
				if(dx < 0 || dx >= N || dy < 0 || dy >= M) continue;
				
				if(visited[dx][dy]) continue;
				
				if(map[dx][dy] == -1) continue;
				
				visited[dx][dy] = true;
				queue.add(new int[] {dx, dy, day+1});
			}
		}
		return day;
	}
}
