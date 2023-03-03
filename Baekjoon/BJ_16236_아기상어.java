import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 아기상어 / 골드3 / 3시간
 * @author 소수현
 * @category BFS
 */

public class BJ_16236_아기상어 {
	
	static class Fish implements Comparable<Fish>{
		int x;
		int y;
		int size;
		
		public Fish(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
		
		@Override
		public int compareTo(Fish o) {
			if(this.x == o.x) return this.y - o.y;
			return this.x - o.x;
		}
	}
	
	// 방향
	static final int[][] directions = {{0,-1}, {-1,0}, {1,0}, {0,1}};
	
	static int N;
	static int[][] space;
	static int[] shark;
	static ArrayList<Fish> fishes;
	static int[] minDis;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 시간
		int time = 0;
		// 공간의 크기 : N
		N = Integer.parseInt(br.readLine());
		
		// 공간
		space = new int[N][N];
		
		// 상어 ; 0번째 인덱스 : x좌표, 1번째 인덱스 : y좌표, 2번째 인덱스 : 크기, 3번째 인덱스 : 거리
		shark = new int[4];
		
		// 물고기들
		fishes = new ArrayList<>();
		
		// 상어보다 작은 물고기들 수
		int littleFishes = Integer.MAX_VALUE;
		
		// 먹은 물고기 수
		int eat = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				
				space[i][j] = val;
				
				// 아기상어
				if(val == 9) {
					shark[0] = i;
					shark[1] = j;
					shark[2] = 2;
					shark[3] = 0;
					space[i][j] = 0;
				}
				// 물고기들
				else if(val > 0) {
					fishes.add(new Fish(i, j, space[i][j]));
				}
			}
		}
		
		// 정렬
		Collections.sort(fishes);
		
		while(littleFishes > 0) {
			// 최소 거리 담는 배열 ; 0번째 인덱스 : 거리, 1번째 인덱스 : 거리 인덱스
			minDis = new int[2];
			minDis[0] = Integer.MAX_VALUE;

			littleFishes = 0;

			for (int i = 0; i < fishes.size(); i++) {
				// 상어보다 작은 물고기들 수 세기
				if(shark[2] > fishes.get(i).size) { 
					littleFishes++;
	
					int dis = bfs(shark, fishes.get(i));
					
					if(dis < minDis[0]) {
						minDis[0] = dis;
						minDis[1] = i;
					}
				}
			}
			
			if(minDis[0] == Integer.MAX_VALUE) break;
			
			if(littleFishes == 0) break;
			
			// 최소 거리인 곳으로 이동
			time += minDis[0];
			
			eat++;
			
			// 상어 위치 조정
			shark[0] = fishes.get(minDis[1]).x;
			shark[1] = fishes.get(minDis[1]).y;
			// 상어 크기 조정
			if(shark[2] == eat) {
				shark[2]++;
				eat = 0;
			}
			
			// 물고기 였던 위치 0으로 조정
			space[fishes.get(minDis[1]).x][fishes.get(minDis[1]).y] = 0;
			// 물고기 삭제
			fishes.remove(minDis[1]);
		}
		
		System.out.println(time);
	}
	
	static int bfs(int[] start, Fish goal) {
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(start);
		
		int sharkSize = start[2];
		int distance = 0;
		visited[start[0]][start[1]] = true;
		
		while(!queue.isEmpty()) {
			int[] poll = queue.poll();
			int x = poll[0];
			int y = poll[1];
			distance = poll[3];
			

			if(x == goal.x && y == goal.y) return distance;
			
			if(distance >= minDis[0]) return Integer.MAX_VALUE;
			
			for (int i = 0; i < 4; i++) {
				int dx = x + directions[i][0];
				int dy = y + directions[i][1];
				
				if(dx < 0 || dx >= N || dy < 0 || dy >= N) continue;
				
				if(visited[dx][dy]) continue;

				if(sharkSize < space[dx][dy]) continue;
				
				visited[dx][dy] = true;
								
				queue.add(new int[] {dx, dy, space[dx][dy], (distance + 1)});
			}
		}
		return Integer.MAX_VALUE;
	}
}
