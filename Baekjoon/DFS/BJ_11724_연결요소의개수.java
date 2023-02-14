package Baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * 실버2 / 40분
 * https://www.acmicpc.net/problem/11724
 *
 */

public class BJ_11724_연결요소의개수 {

	static LinkedList<Integer>[] list;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		list = new LinkedList[N+1];
		visited = new boolean[N+1];
		
		int count = 0;
		
		// 인접리스트 생성
		for(int i = 1; i <= N; i++) {
			list[i] = new LinkedList<>();
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			// 무방향 그래프 이므로 서로 넣어준다.
			list[u].add(v);
			list[v].add(u);
		}

		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				count++;
				dfs(i);
			}
		}
		
		System.out.println(count);
	}
	
	public static void dfs(int next) {
		if(visited[next]) {
			return;
		}
		
		// 방문하지 않은 노드일 때
		visited[next] = true;

		for(int i = 0; i < list[next].size(); i++) {
			int idx = list[next].get(i);
			if(!visited[idx]) {
				dfs(idx);
			}
		}
	}
}
