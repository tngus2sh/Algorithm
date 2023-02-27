package Baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1012_유기농배추 {
	
	static final int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	// dfs 방문했는지 체크하는 변수
	static boolean[][] visitedD;
	// bfs 방문했는지 체크하는 변수
	static boolean[][] visitedB;
	static int N, M;
	static int[][] cabbageField;
	
	public static void main(String[] args) throws IOException {
		/*
		 * 인접한 배추들을 한 구역이라고 생각하고 구역의 개수를 구하면 되는 문제
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			
			// M : 배추를 심은 배추밭의 가로길이, N : 세로길이, K : 배추가 심어져 있는 위치의 개수
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			// 배추밭
			cabbageField = new int[M][N];
			
			// [초기화]
			visitedD = new boolean[M][N];
			visitedB = new boolean[M][N];
			
			// X, Y : 배추의 위치
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				
				// 배추 위치 표시
				cabbageField[X][Y] = 1;
			}
			
			// step1. dfs
			// 테스트 케이스마다 지렁이의 개수를 센다.
			int countD = 0;
			
			// 가로 세로 좌표들을 하나씩 입력해준다.
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					
					// 좌표에 배추가 있는지 확인, 내가 체크 안한 곳인지 확인한다.
					if(cabbageField[i][j] == 1 && !visitedD[i][j]) {
						// 배추가 있고 체크안된 좌표에서부터 dfs로 연결된 곳을 파악한다.
						dfs(i,j);
						countD++;
					}
					
				}
			}
			
			// step2. bfs
			int countB = 0;
			
			// 가로 세로 좌표들을 하나씩 입력해준다.
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					
					// 좌표에 배추가 있는지 확인, 내가 체크 안한 곳인지 확인한다.
					if(cabbageField[i][j] == 1 && !visitedB[i][j]) {
						// 배추가 있고 체크안된 좌표에서부터 bfs로 연결된 곳을 파악한다.
						bfs(i,j);
						countB++;
					}
					
				}
			}
			
			
			System.out.println(countD);
//			System.out.println(countB);
		}
	}
	
	/** 배추의 구역인지 탐색 : dfs */
	public static void dfs(int x, int y) {
		visitedD[x][y] = true;
		// 시작 좌표엔 배추가 있으니 미리 true로 처리
		
		
		// 상하좌우 4가지 방법이니 for문 4번 반복
		for (int i = 0; i < 4; i++) {
			int dx = x + directions[i][0];
			int dy = y + directions[i][1];
			
			if(dx < 0 || dx >= M || dy < 0 || dy >= N) {
				continue;
			}
			
			// 상하좌우 움직인 좌표에 배추가 있고, 체크하지 않은 좌표이면
			if(cabbageField[dx][dy] == 1 && !visitedD[dx][dy]) {
				// 해당 좌표로 dfs 실행
				dfs(dx, dy);
			}
		}
		
	}
	
	/** 배추의 구역인지 탐색 : bfs */
	public static void bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		// bfs에서 queue의 역할은 다음 탐색할 좌표를 미리 저장해 놓는 것
		// bfs 1번 실행될 때마다 인접한 곳을 모두 탐색하고 종료 => bfs안에 queue를 선언
		
		queue.offer(new int[] {x, y});
		// x, y좌표 저장
		
		visitedB[x][y] = true;
		// 시작좌표엔 배추가 있으니 미리 true로 처리해준다.
		
		// queue가 비어있으면 더이상 인접한 배추가 없다는 뜻
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			// 저장된 queue를 꺼낸다.
			
			for (int d = 0; d < 4; d++) {
				int dx = poll[0] + directions[d][0];
				int dy = poll[1] + directions[d][1];
				
				if(dx < 0 || dx >= M || dy < 0 || dy >= N) {
					continue;
				}
				
				// 상하좌우 움직인 좌표에 배추가 있고, 체크하지 않은 좌표이면
				if(cabbageField[dx][dy] == 1 && !visitedB[dx][dy]) {
					queue.offer(new int[] {dx, dy});
					// 좌표 저장
					visitedB[dx][dy] = true;
					// 방문한 곳 체크
				}
			}
		}
		
		
	}
}
 
