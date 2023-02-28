package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 경로찾기 / 실버1 / 20분
 * https://www.acmicpc.net/problem/11403
 */

public class BJ_11403_경로찾기 {
	
	static int N, index;
	static int[][] adjacent;
	static boolean[] visited;
	static int[][] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// N : 정점의 개수
		N = Integer.parseInt(br.readLine());
		
		
		// 인접 행렬               
		adjacent = new int[N][N];
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			
			for (int m = 0; m < N; m++) {
				adjacent[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 방문 체크 초기화
		visited = new boolean[N];
		
		// 정답 인접행렬
		result = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			index = i;
			dfs(i);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	public static void dfs(int n) {
		for (int i = 0; i < N; i++) {
			if(!visited[i] && adjacent[n][i] == 1) {
				visited[i] = true;
				result[index][i] = 1;
				dfs(i);
			}
		}
	}
}
