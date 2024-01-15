package Baekjoon.BFS;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 숨바꼭질2 / 골드4 / 1시간 32분 / 24.01.15
 */

public class BJ_12851_숨바꼭질2 {

    public static class Catch{
        int position;
        int time;
        public Catch(int position, int time) {
            this.position = position;
            this.time = time;
        }
    }
    public static int min;
    public static int cnt;
    public static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        min = INF;
        cnt = 0;

        bfs(N, K);

        System.out.println(min);
        System.out.println(cnt);
    }

    public static void bfs(int N, int K) {
        Queue<Catch> queue = new LinkedList<>();
        queue.offer(new Catch(N, 0));

        // 방문배열
        int[] visited = new int[2000001];

        while(!queue.isEmpty()) {
            Catch subin = queue.poll();

            if (subin.position == K) {
                // 최소값과 같은지 확인 -> 같다면 방법 개수 업
                if (min == subin.time) cnt++;
                else if (min == INF) {
                    min = subin.time;
                    cnt++;
                }
            }

            if (min < INF && subin.time > min) return;

            if (subin.position < 0 || subin.position > 200000) continue;

            // 1. 최초 기록이 0초이거나 이전 기록보다 빠를 때
            if (visited[subin.position] == 0 || visited[subin.position] > subin.time) {
                visited[subin.position] = subin.time + 1;
                queue.offer(new Catch(subin.position - 1, subin.time + 1));
                queue.offer(new Catch(subin.position + 1, subin.time + 1));
                queue.offer(new Catch(subin.position*2, subin.time +1 ));
            }
            // 2. 이전 기록보다 느리면 패쓰
        }
    }
}
