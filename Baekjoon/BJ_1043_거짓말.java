import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 거짓말 / 골드4 / 40분
 * https://www.acmicpc.net/problem/1043
 */

public class BJ_1043_거짓말 {
	static int N, t;
	static int[] parents;
	static HashMap<Integer, Boolean> truth;
	
	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
				
		if(aRoot == bRoot) return false;
		// 만약 둘 중에 하나라도 진실을 아는 사람이라면 그 사람 위주로 합쳐준다.
		boolean check = false;
		for (int i = 0; i < t; i++) {
			if(truth.containsKey(aRoot)) {
				check = true;
				parents[bRoot] = aRoot;
			}
			else if(truth.containsKey(bRoot)) {
				check = true;
				parents[aRoot] = bRoot;
			}
		}
		if(!check) {
			parents[bRoot] = aRoot;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 사람의 수 : N, 파티의 수 : M
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 진실을 아는 사람의 수 : t , 번호
		st = new StringTokenizer(br.readLine());
		t  = Integer.parseInt(st.nextToken());
		
		// 진실을 아는 사람의 배열
		truth = new HashMap<>();
		for (int i = 0; i < t; i++) {
			int key = Integer.parseInt(st.nextToken());
			truth.put(key, true);	
		}
		
		parents = new int[N+1];
		makeSet();
		
		// 파티에 오는 사람의 수 : p, 파티에 오는 사람의 번호 : people
		// 파티 담는 배열
		ArrayList<Integer>[] party = new ArrayList[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			// 파티 담는 배열 초기화
			party[i] = new ArrayList<>();
			for (int j = 0; j < p; j++) {
				int people = Integer.parseInt(st.nextToken());
				party[i].add(people);
				// 같은 파티에 있는 사람들은 같은 집합으로 묶어줌
				if(j > 0) {
					union(party[i].get(j), party[i].get(j-1));
				}
			}
		}
		// 갈수 있는 파티 개수
		int iGoToParty = 0;
		
		// 만약 포함되어 있는 제일 위 루트가 진실을 아는 사람이 있는 파티라면 안 감
		boolean canGo = true;
		for (int i = 0; i < M; i++) {
			canGo = true;
			for (int j = 0; j < party[i].size(); j++) {
				int people = party[i].get(j);
				if(truth.containsKey(findSet(people))) canGo = false;
			}
			
			if(canGo) iGoToParty++;
		}
		
		System.out.println(iGoToParty);
	}
}
