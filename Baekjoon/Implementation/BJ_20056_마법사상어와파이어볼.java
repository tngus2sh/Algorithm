package Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 마법사 상어와 파이어볼 / 골드 4 / 3시간
 * https://www.acmicpc.net/problem/20056
 */
public class BJ_20056_마법사상어와파이어볼 {	
	/** 파이어볼 클래스 */
	static class FireBall {
		int x;
		int y;
		int weight;
		int speed;
		int dir;
		
		public FireBall(int x, int y, int weight, int speed, int dir) {
			this.x = x;
			this.y = y;
			this.weight = weight;
			this.speed = speed;
			this.dir = dir;
		}
	}
	static final int[][] directions = {{-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}, {-1,-1}};
	static int N;
	static int[][] map;
	static ArrayList<FireBall> fireballs;
	static Stack<int[]> pos;
	static ArrayList<ArrayList<Integer>> merges;
	
	/** 파이어볼 이동 */
	static void moveBalls() {
		int size = fireballs.size();
		for (int i = 0; i < size; i++) {
			int nx = fireballs.get(i).x;
			int ny = fireballs.get(i).y;
			int speed = fireballs.get(i).speed;
			int dir = fireballs.get(i).dir;
			
			nx += ((speed%N) * directions[dir][0]);
			ny += ((speed%N) * directions[dir][1]);
			
			if(nx < 0 || nx >= N) nx = (nx+N)%N;
			if(ny < 0 || ny >= N) ny = (ny+N)%N;
			
			// 값 다시 넣어주기
			fireballs.get(i).x = nx;
			fireballs.get(i).y = ny;
			
			if(map[nx][ny] >= 1) map[nx][ny] = 2;
			else map[nx][ny] = 1;
		}
	}
	/** 합쳐진 파이어볼이 있는지 확인 */
	static boolean mergeBalls() {
		pos = new Stack<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] > 1) {
					pos.add(new int[] {i, j});
				}
			}
		}
		if(pos.size() <= 0) return false;
		int idx = 0;
		merges = new ArrayList<>();
		while(!pos.isEmpty()) {
			merges.add(idx, new ArrayList<>());
			int[] pop = pos.pop();
			int popX = pop[0];
			int popY = pop[1];
			for (int i = 0; i < fireballs.size(); i++) {
				int x = fireballs.get(i).x;
				int y = fireballs.get(i).y;
				
				if(x == popX && y == popY) {
					merges.get(idx).add(i);
				}
			}
			idx++;
		}
		return true;
	}
	/** 파이어볼 나누기 */
	static void divideBalls() {
		ArrayList<Integer> stack = new ArrayList<>();
		// 질량 : (합쳐진 질량)/5, 속력 : (합쳐진 속력)/개수, 방향 : 모두 홀수 or 짝수 -> 0,2,4,6
		for (int i = 0; i < merges.size(); i++) {
			int size = merges.get(i).size();
			int x = -1;
			int y = -1;
			int sumWeight = 0;
			int sumSpeed = 0;
			boolean isOdd = false;
			boolean isEven = false;
			int[] dirss = new int[4];
			for (int j = 0; j < merges.get(i).size(); j++) {
				stack.add(merges.get(i).get(j));
				int idx = merges.get(i).get(j);
				x = fireballs.get(idx).x;
				y = fireballs.get(idx).y;
				int weight = fireballs.get(idx).weight;
				int speed = fireballs.get(idx).speed;
				int dir = fireballs.get(idx).dir;
				
				sumWeight += weight;
				sumSpeed += speed;
				
				if(dir%2 == 0) isEven = true;
				else isOdd = true;
			}
			sumWeight = sumWeight/5;
			// 질량이 0이라면 소멸
			if(sumWeight > 0) {
				sumSpeed = sumSpeed/size;
				if(isOdd && isEven) {
					for (int j = 0; j < 4; j++) {
						dirss[j] = 2*j + 1;
					}
				} else {
					for (int j = 0; j < 4; j++) {
						dirss[j] = 2*j;
					}
				}
				// 새로운 파이어볼 생성
				for (int j = 0; j < 4; j++) {
					fireballs.add(new FireBall(x, y, sumWeight, sumSpeed, dirss[j]));				
				}
			}
			
		}
		
		Collections.sort(stack);
		// 기존에 있던 애들은 없앰
		for (int j = stack.size()-1; j >= 0; j--) {
			int poll = stack.get(j);
			fireballs.remove(poll);			
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 격자 크기 : N, 파이어볼 개수 : M, 이동 횟수 : K
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 파이어볼 리스트
		fireballs = new ArrayList<>();
		// 파이어볼 정보 : (행,열), 질량, 속력, 방향
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			fireballs.add(new FireBall(row-1, col-1, weight, speed, dir));
		}
		// 합쳐진 좌표 넣어주는 
		// k번 이동
		for (int k = 0; k < K; k++) {
			map = new int[N][N];
			// 파이어볼 이동시키기
			moveBalls();
			// 합쳐진 파이어볼이 있으면 볼 나누기
			if(mergeBalls()) divideBalls();
		}
		// 남아있는 파이어볼 질량의 합 구하기
		int result = 0;
		for (int i = 0; i < fireballs.size(); i++) {
			result += fireballs.get(i).weight;
		}
		System.out.println(result);
	}
}
