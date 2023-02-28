package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 안전영역 / 실버 1 / 30분
 * https://www.acmicpc.net/problem/2468
 */
public class BJ_2468_안전영역 {
	
	static int N;
	static int[][] region;
	static boolean[][] visited;
	static final int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 행과 열의 개수 : N
		N = Integer.parseInt(br.readLine());
		
		// 지역
		region = new int[N][N];
		
		// 최소 높이
		int minHeight = Integer.MAX_VALUE;
		
		// 최대 높이
		int maxHeight = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				region[i][j] = Integer.parseInt(st.nextToken());
				
				minHeight = Math.min(minHeight, region[i][j]);
				maxHeight = Math.max(maxHeight, region[i][j]);
			}
		}
		
		
		// 안전지역 최대개수
		int safe = 0;
		
		int idx = 0;
		
		while(minHeight + idx <= maxHeight) {
			int num = 0;

			// 방문체크 초기화
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					
					// 방문하지 않았고, 지역의 높이가 최소 높이 + idx 보다 높으면 안전영역
					if(!visited[i][j] && region[i][j] > minHeight + idx) {
						dfs(i, j, minHeight + idx);
						
						// dfs를 하고 나온 만큼이 안전영역
						num++;
					}
				}
			}
			
			// 만약 다 같은 높이라면 잠기지 않으니까 num의 값을 1로 바꿔준다.
			if(num == 0) num = 1;
			
			// 안전영역의 최대값 저장
			safe = Math.max(safe, num);
			idx++;
		}
		
		System.out.println(safe);
	}
	
	/** 안전영역 DFS 탐색 */
	public static void dfs(int x, int y, int flag) {
		visited[x][y] = true;
		
		for (int d = 0; d < 4; d++) {
			int dx = x + directions[d][0];
			int dy = y + directions[d][1];
			
			if(dx < 0 || dx >= N || dy < 0 || dy >= N) continue;
			
			// 방문하지 않았고, 지역이 넘겨준 높이보다 높으면 안전영역
			if(!visited[dx][dy] && region[dx][dy] > flag) {
				dfs(dx, dy, flag);
			}
		}
	}
}
