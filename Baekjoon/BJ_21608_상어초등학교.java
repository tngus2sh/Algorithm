import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_21608_상어초등학교 {
	static final int[][] directions = {{-1,0}, {0,-1}, {0,1}, {1,0}};
	static int[][] map;
	static int N, good;
	static HashMap<Integer, int[]> students;
	static HashMap<Integer, int[]> studentPos;
	static int[] satisfy;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 격자의 크기
		N = Integer.parseInt(br.readLine());
		
		// 격자
		map = new int[N][N];
		
		// 순서
		int[] sequence = new int[N*N];
		
		// 학생과 학생이 좋아하는 학생 4명의 번호
		students = new HashMap<>();
		for (int i = 1; i <= N*N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int key = Integer.parseInt(st.nextToken());
			int[] likeStudents = new int[4];
			for (int j = 0; j < 4; j++) {
				likeStudents[j] = Integer.parseInt(st.nextToken());
			}
			
			students.put(key, likeStudents);
			sequence[i-1] = key;
		}
		
		// 학생 좌표 초기화
		studentPos = new HashMap<>();
		studentPos.put(sequence[0], new int[] {1,1});
		map[1][1] = sequence[0];
		
		satisfy = new int[(N*N)+1];
		
		System.out.println(good);
	}
}
