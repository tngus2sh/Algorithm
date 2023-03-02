import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 테트로미노 / 골드4 / 1시간
 * https://www.acmicpc.net/problem/14500
 */
public class BJ_14500_테트로미노 {

	static final int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
	static int N,M;
	static int[][] paper;
	static boolean[][] visited;
	static boolean[] choose;
	static int maxSum = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 종이의 세로 크기 : N , 가로 크기 : M
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 종이
		paper = new int[N][M];

		// 방문체크
		visited = new boolean[N][M];
		// 선택 체크
		choose = new boolean[4];
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				paper[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 정사각형 4개 이어붙임 -> 테트로미노
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tetromino(0, i, j, 0);
				fe(0, i,j);
			}
		}

		System.out.println(maxSum);
	}

	/** 철 자를 제외한 나머지 테트로미노 탐색 */
	static void tetromino(int cnt, int x, int y, int sum) {
		if (cnt == 4) {
			maxSum = Math.max(maxSum, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int dx = x + directions[i][0];
			int dy = y + directions[i][1];

			if (dx < 0 || dx >= N || dy < 0 || dy >= M) continue;

			if (!visited[dx][dy]) {
				visited[dx][dy] = true;
				tetromino(cnt + 1, dx, dy, sum + paper[dx][dy]);
				visited[dx][dy]= false;
			}
		}
	}

	/**철 자 선택하는 메소드 */
	static void fe(int cnt, int x, int y) {
		if(cnt == 3) {
			sum(x, y);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if(!choose[i]) {
				choose[i] = true;
				fe(cnt + 1, x, y);
				choose[i] = false;
			}
		}
	}

	/** 철 자 합하는 메소드 */
	static void sum(int x, int y) {
		int total = 0;

		// 현재 자리도 total 포함시켜야 함
		total += paper[x][y];

		for (int i = 0; i < 4; i++) {
			if(choose[i]) {
				int dx = x + directions[i][0];
				int dy = y + directions[i][1];

				// 범위 벗어난 경우 종료
				if(dx < 0 || dx>= N || dy < 0 || dy>= M) return;

				total += paper[x+directions[i][0]][y+directions[i][1]];
			}
		}

		maxSum = Math.max(maxSum, total);
	}
}
