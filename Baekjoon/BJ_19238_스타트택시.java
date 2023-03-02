import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_19238_스타트택시 {
	
	static class Client implements Comparable<Client>{
		int[] clientPos;
		int[] goalPos;
		int index;
		
		public Client(int[] clientPos, int[] goalPos, int index) {
			this.clientPos = clientPos;
			this.goalPos = goalPos;
			this.index = index;
		}
		@Override
		public int compareTo(Client o) {
			if(this.clientPos[0] - o.clientPos[0] == 0) return this.clientPos[1] - o.clientPos[1];
			return this.clientPos[0] - o.clientPos[0];
		}
	}
	
	static int[] driverPos;
	static final int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static int N;
	static int[][] map;
	static boolean[][] visitedMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 세로 : N, 가로 : M, 초기 연료의 양 : fuel
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int fuel = Integer.parseInt(st.nextToken());
		
		// 지도
		map = new int[N][N];
		
		// 방문처리 초기화
		visitedMap = new boolean[N][N];
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < N; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 운전을 시작하는 행, 열 번호
		st = new StringTokenizer(br.readLine());
		int driverRow = Integer.parseInt(st.nextToken());
		int driverCol = Integer.parseInt(st.nextToken());
		driverPos = new int[] {driverRow-1, driverCol-1};
		
		// 손님 행,  열 번호
//		PriorityQueue<Client> clients = new PriorityQueue<>();
		Client[] clients = new Client[M];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());

//			clients[m] = new Client();
			
			int clientRow = Integer.parseInt(st.nextToken());
			int clientCol = Integer.parseInt(st.nextToken());
			int goalRow = Integer.parseInt(st.nextToken());
			int goalCol = Integer.parseInt(st.nextToken());
			
//			clients.add(new Client(new int[] {clientRow, clientCol}, new int[] {goalRow, goalCol}, m));
			
			clients[m].clientPos = new int[] {clientRow-1, clientCol-1};
			clients[m].goalPos = new int[] {goalRow-1, goalCol-1};
			clients[m].index = m;
			
		}
		
		boolean[] visited = new boolean[M];
		int clientsCnt = M;
		//결과
		int result = 0;
		while(true) {
			int minDis = Integer.MAX_VALUE;
			int minIdx = clientsCnt;
			PriorityQueue<Integer> min = new PriorityQueue<>();
			
			for (int i = 0; i < M; i++) {
				// 방문하지 않은 승객에 한해서만 진행
				if(!visited[i]) {
					visitedMap = new boolean[N][N];
					
					int dis = bfs(driverPos, clients[i].clientPos);
					// -- debug
//					System.out.println("i : " + i + ", dis : " + dis);
					// 만약 길을 못 찾는다면 그대로 -1 출력하고 종료
					if(dis == -1) { System.out.println(-1); return; }
					
					// 최소 거리 갱신
					if(dis == minDis) {
						min.add(i);
					} else if(dis < minDis) {
						min = new LinkedList<>();
						min.add(i);
						minDis = dis;
						minIdx = i;
					}
				}
			}
			visitedMap = new boolean[N][N];
			
//			System.out.println("minDis : " + minDis);
			
			minIdx = min.get(0);
			
			
//			System.out.println("fuel : " + fuel);
			// 연료 줄이기
			fuel -= minDis;
			// 승객 방문 처리
			visited[minIdx] = true;
			// 승객 수 줄이기
			clientsCnt--;
			
			// -- debug
//			System.out.println("minIdx : " + minIdx + " , fuel : " + fuel);
			// 목적지까지 이동
			int f = bfs(clients[minIdx].clientPos, clients[minIdx].goalPos);
			
//			System.out.println("f : " + f);

			// 만약 연료가 중간에 바닥났는데 주변이 벽으로 다 둘러싸여있으면 이동 못하니까 -1을 리턴하게 됨
			if(f == -1) { System.out.println(-1); return; }
			
			// 연료가 바닥나면 그대로 반복문 벗어나고 종료
			fuel -= f;
			
			// -- debug
//			System.out.println("ClientsCnt : " + clientsCnt + ", fuel : " + fuel);
			
			if(fuel < 0) { result = -1; break; }
			
			// 연료 충전
			fuel += f*2;
//			System.out.println("charge fuel : " + fuel);
//			System.out.println("=================================================");
			
			// 승객을 다 돌면 그대로 종료
			if(clientsCnt == 0) { result = fuel; break; }
			
			// 운전자 좌표 조정
			driverPos = clients[minIdx].goalPos;
			
		}
		System.out.println(result);
	}
	
	static int bfs(int[] startPos, int[] goalPos) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {startPos[0], startPos[1] , 0});

		int loopCnt = 0;

		while(!queue.isEmpty()) {
			int[] pos = queue.poll();

			if(pos[0] == goalPos[0] && pos[1] == goalPos[1]) return pos[2];
			
			if(loopCnt == N*N) return -1;
			
			int distance = pos[2];
			
			for (int i = 0; i < 4; i++) {
				int dx = pos[0] + directions[i][0];
				int dy = pos[1] + directions[i][1];
				
				if(dx < 0 || dx >= N || dy < 0 || dy >= N) continue;
				
				// 지나갈수 있는 길일 때
				if(map[dx][dy] == 0) {
					if(!visitedMap[dx][dy]) {
						visitedMap[dx][dy] = true;
						queue.add(new int[] {dx, dy, distance + 1});
					}
				}
			}
			loopCnt++;
		}
		return -1;
	}

}
