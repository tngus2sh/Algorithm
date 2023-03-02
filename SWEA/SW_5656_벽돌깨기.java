import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class SW_5656_벽돌깨기 {
	static int W, H;
	static int[][] blocks, bricks;
	static int[] top, combi;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			blocks = new int[H][W];
			bricks = new int[H][W];
			
			// 꼭대기 위치
			top = new int[W];
			
			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					blocks[h][w] = Integer.parseInt(st.nextToken());
					
					if(blocks[h][w] != 0) {
						// 꼭대기 좌표 넣기
						top[w] = h;
					}
				}
			}
			
			combi = new int[N];
			pickLine(N, 0);
			
			for (int n = 0; n < N; n++) {
				bomb();
			}
			
		}
		
	}
	
	static void pickLine(int r, int cnt) {
		if(r == 0) {
			for (int i = 0; i < H; i++) {
				bricks[i] = blocks[i].clone();
			}
			visited = new boolean[H][W];
			bomb();
			return;
		}
		for(int i = 0; i < W; i++) {
			combi[cnt] = i;	
			pickLine(r-1, cnt+1);
		}
	}
	
	static void bomb() {
		// combi에 부셔야 하는 열이 있음 -> 하나씩 반복문으로 가져와서
		// 부셔지는 벽돌을 큐로 체크하고 실제로 없애기

		ArrayDeque<Point> queue = new ArrayDeque<>();
		Point point;
		int depth;
		int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
		
		for (int i = 0; i < combi.length; i++) {
			queue.add(new Point(top[combi[i]], combi[i]));
			
			visited[top[combi[i]]][combi[i]] = true;
			
			while(!queue.isEmpty()) {
				point = queue.poll();
				depth = bricks[point.x][point.y];
				
				
				for (int j = 0; j < depth-1; j++) {
					for (int d = 0; d < 4; d++) {
						int dx = point.x + directions[d][0];
						int dy = point.y + directions[d][1];
						
						if(dx < 0 || dx >= H || dy < 0 || dy >= W) continue;
						
						if(!visited[dx][dy]) {
							visited[dx][dy] = true;
							queue.add(new Point(dx, dy));
						}
					}
				}
			}
		}
	}

}
