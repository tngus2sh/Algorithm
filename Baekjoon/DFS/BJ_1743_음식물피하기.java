package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 음식물 피하기 / 실버 1 / 20분
 * https://www.acmicpc.net/problem/1743
 */
public class BJ_1743_음식물피하기 {
	
	static final int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static boolean[][] visited;
	static int N, M;
	static int[][] map;
	static int area = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 통로의 세로 길이 : N
		N = Integer.parseInt(st.nextToken());
		// 통로의 가로 길이 : M
		M = Integer.parseInt(st.nextToken());
		// 음식물 쓰레기의 개수 : K
		int K = Integer.parseInt(st.nextToken());
		
		
		// 8층
		map = new int[N+1][M+1];
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 음식물 표시
			map[x][y] = 1;
		}
		
		// 방문 체크 초기화
		visited = new boolean[N+1][M+1];
		
		// 결과값
		int result = 1;
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				
				// 방문하지 않았고, 음식물 쓰레기가 있는 위치라면 dfs고
				if(!visited[i][j] && map[i][j] == 1) {
					// 들어갈 때마다 영역 크기 재는 변수 초기화
					area = 1;
					
					dfs(i, j);
					
					// dfs돌고 나와서 area 값들을 비교해 제일 큰 값 넣음
					result = Math.max(result, area);
				}
			}
		}
		
		System.out.println(result);
	}
	
	static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			int dx = x + directions[i][0];
			int dy = y + directions[i][1];
			
			if(dx < 1 || dx > N || dy < 1 || dy > M) continue;
			
			// 방문하지 않았고, 음식물 쓰레기인 경우 dfs고
			if(!visited[dx][dy] && map[dx][dy] == 1) {
				// 이 해당 if문에 들어왔다는 건 음식물 쓰레기이고, 위의 조건을 만족 시켰으므로 영역에 +1 을 해준다.
				area++;
				dfs(dx, dy);
			}
		}
	}
}
