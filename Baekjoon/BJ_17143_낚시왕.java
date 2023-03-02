import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_17143_낚시왕 {
	static class Shark implements Comparable<Shark> {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Shark o) {
			if(this.c == o.c) return this.r - o.r;
			return this.c - o.c;
		}

		@Override
		public String toString() {
			return "Shark [r=" + r + ", c=" + c + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}
	}
	
	static LinkedList<Shark> sharks;
	static int person = 1;
	// 결과변수
	static int result = 0;
	static int R, C;
	
	static void catchShark() {
		for (int i = 0; i < sharks.size(); i++) {
			// shark의 열과 사람의 열이 같으면 잡아먹기~~
			if(sharks.get(i).c == person) {
				result += sharks.get(i).z;
				
//				// -- debug
//				System.out.println("result : " + result + ", shark size : "+ sharks.get(i).z);
				
				sharks.remove(i);
				break;
			}
		}
	}
	
	static void moveShark() {
		int[][] d = {{}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		Shark shark;
		for (int i = 0; i < sharks.size(); i++) {
			shark = sharks.get(i);
			for(int k = 0; k < shark.s; k++) {
				int dx = shark.r + d[shark.d][0];
				int dy = shark.c + d[shark.d][1];
				
				// 범위 벗어났을 때 방향 바꿔주기
				if(dx < 1 || dx > R || dy < 1 || dy > C) {
					if(shark.d == 1 || shark.d == 3) {
						shark.d = shark.d + 1;
					} else {
						shark.d = shark.d - 1;
					}
					
					dx = shark.r + d[shark.d][0];
					dy = shark.c + d[shark.d][1];
				}
				
				sharks.get(i).r = dx;
				sharks.get(i).c = dy;
 			}
		}
	}
	
	static void eatShark() {
		Shark shark;
		ArrayList<Integer> idx = new ArrayList<>();
		for (int i = 0; i < sharks.size()-1; i++) {
			shark = sharks.get(i);
			for (int j = i+1; j < sharks.size(); j++) {
				// 상어의 위치가 같을 때
				if(shark.r == sharks.get(j).r && shark.c == sharks.get(j).c) {
					// 오른쪽 상어가 더 클 때
					if(shark.z < sharks.get(j).z) {
						idx.add(i);
					} else {
						idx.add(j);
					}
				}
			}
		}
		
		// index 정렬
		Collections.sort(idx);
		
		// 상어 삭제
		for (int i = idx.size()-1; i >= 0; i--) {
			sharks.remove((int)idx.get(i));
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		sharks = new LinkedList<>();
		int r, c, s, d, z;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			sharks.add(new Shark(r, c, s, d, z));
		}
		
		Collections.sort(sharks);
		
//		// -- debug
//		System.out.println("shark : " + sharks.toString());
		
		for(int i = 1; i <= C; i++) {
			catchShark();
			moveShark();
			Collections.sort(sharks);			
			eatShark();
			person++;
			
//			// -- debug
//			System.out.println("shark : " + sharks.toString());
		}
		
		System.out.println(result);
	}
}
