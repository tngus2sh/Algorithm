import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14500_테트로미노 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 종이의 세로 크기 : N , 가로 크기 : M
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 종이
		int[][] paper = new int[N][M];
		
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				paper[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 정사각형 4개 이어붙임 -> 테트로미노
		
		
	}
	
	static void tetromino() {
		
	}
}
