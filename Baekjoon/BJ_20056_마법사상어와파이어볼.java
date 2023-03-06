import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_20056_마법사상어와파이어볼 {
	static class FireBall {
		int x;
		int y;
		int weight;
		int dis;
		int dir;
		public FireBall(int x, int y, int weight, int dis, int dir) {
			this.x = x;
			this.y = y;
			this.weight = weight;
			this.dis = dis;
			this.dir = dir;
		}
	}
	static final int[][] directions = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};
	static ArrayList<FireBall>[][] overlapPos;
	static ArrayList<int[]> pos;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 격자 크기 : N, 파이어볼 개수 : M, 이동횟수 : K
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 겹쳐있는 위치
		overlapPos = new ArrayList[N][N];
		pos = new ArrayList<>();
		// 위치 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				overlapPos[i][j] = new ArrayList<>();
			}
		}
		// 파이어볼 리스트
		ArrayList<FireBall>  fireballs =  new ArrayList<>();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			fireballs.add(new FireBall(x-1, y-1, weight, dis, dir));
		}
		// 1. 모든 파이어볼이 자신의 방향 d로 속력 s칸만큼 이동
		FireBall fireball;
		for (int m = 0; m < M; m++) {
			fireball = fireballs.get(m);
			// 위치 이동
			int dx = fireball.x + directions[fireball.dir][0];
			int dy = fireball.y + directions[fireball.dir][1];
			// 만약 행의 범위를 벗어난다면 다시 행의 앞에서 시작
			if(dx >= N || dx < 0) dx = (dx + N)%N;
			// 열의 범위를 벗어난다면 다시 열의 앞에서 시작
			if(dy >= N || dy < 0) dy = (dy + N)%N;
			// 파이어볼 위치 조정
			fireball.x = dx;
			fireball.y = dy;
			// 겹쳐있는 위치
			if(overlapPos[dx][dy].size() > 0) pos.add(new int[] {dx, dy});
			// 위치 넣기
			overlapPos[dx][dy].add(fireball);
		}
	}
}
