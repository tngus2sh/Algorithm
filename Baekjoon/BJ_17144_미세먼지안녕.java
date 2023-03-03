import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17144_미세먼지안녕 {
	
	static int R, C;
	static int[][] home;
	static int[][] homeUp, homeDown;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 집 세로 : R , 가로 : C
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// T초
		int T = Integer.parseInt(st.nextToken());
		
		home = new int[R][C];
		
		// 공기 청정기
		int[][] air = new int[2][2];
		int idx = 0;
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
				
				// 공기청정기
				if(home[i][j] == -1) {
					air[idx][0] = i;
					air[idx][1] = j;
					idx++;
				}
			}
		}
		
		// 공기청정기 기준 위아래
		int up = air[0][0];
		int down = R - air[1][0];
		homeUp = new int[up][C];
		homeDown = new int[down][C];
		
		copyArray(up, down);

	}
	
	// 배열 복사
	static void copyArray(int up, int down) {
		for (int i = 0; i < up; i++) {
			homeUp[i] = home[i].clone();
		}
		
		for (int i = 0; i < down; i++) {
			homeDown[i] = home[i + up].clone();
		}
	}
	
	static void spreadDust(int[] start) {
		int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};
		int x = start[0];
		int y = start[1];
		int size = home[x][y];
		int count = 0;
		
		for (int i = 0; i < 4; i++) {
			int dx = x + directions[i][0];
			int dy = y + directions[i][1];
			
			if(dx < 0 || dx >= R || dy < 0 || dy >= C) continue;
			
			count++;
		}
		
	}
}
