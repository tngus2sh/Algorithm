package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 스타트택시 / 골드2 / 2시간
 * https://www.acmicpc.net/problem/19238
 */
public class BJ_19238_스타트택시 {
    static class Client implements Comparable<Client> {
        int[] startPos;
        int[] goalPos;
        public Client(int[] startPos, int[] goalPos){
            this.startPos = startPos;
            this.goalPos = goalPos;
        }
        @Override
        public int compareTo(Client o) {
            if(this.startPos[0] == o.startPos[0]) return this.startPos[1] - o.startPos[1];
            return this.startPos[0] - o.startPos[0];
        }
    }
    static int N, M, minDis;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 세로 : N, 가로 : M, 초기 연료의 양 : originalFuel
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int originalFuel = Integer.parseInt(st.nextToken());

        // 지도
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] driverPos = new int[2];
        // 운전을 시작하는 칸의 행 번호, 열 번호
        st = new StringTokenizer(br.readLine());
        driverPos[0] = Integer.parseInt(st.nextToken()) - 1;
        driverPos[1] = Integer.parseInt(st.nextToken()) - 1;

        ArrayList<Client> clients = new ArrayList<>();
        // M개의 승객 출발지의 행, 열 번호 & 목적지의 행, 열 번호
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int goalX = Integer.parseInt(st.nextToken());
            int goalY = Integer.parseInt(st.nextToken());
            clients.add(new Client(new int[] {startX-1, startY-1}, new int[] {goalX-1 , goalY-1}));
        }
        // 승객 정렬
        Collections.sort(clients);
        // 최소 거리
        minDis = Integer.MAX_VALUE;
        // 최소 거리 인덱스
        int minDisIdx = -1;
        // 승객 수
        int clientNum = clients.size();
        // 결과
        int result = -1;

        while(clientNum > 0) {
            // 각 승객마다 돌아가면서 거리를 구한다
            for (int i = 0; i < clientNum; i++) {
                int dis = bfs(driverPos, clients.get(i).startPos);
                // 만약 이동을 못하는 손님이라면 그대로 반복문 종료
                if(dis == -1){
                    System.out.println(-1);
                    return;
                }
                if(minDis > dis) {
                    minDis = dis;
                    minDisIdx = i;
                }
            }
            // 최소 거리를 뽑음
            // 연료의 양에 최소 거리를 빼고 연료의 양이 0보다 큰지 확인
            originalFuel = originalFuel - minDis;
            if(originalFuel < 0) break;
            // 0보다 크다면 운전자의 위치를 승객의 위치로 이동
            driverPos = clients.get(minDisIdx).startPos;
            // 목적지로 이동
            int fuel = bfs(driverPos, clients.get(minDisIdx).goalPos);
            // 목적지로 이동한 만큼의 연료를 빼고 연료의 양이 0보다 큰지 확인
            originalFuel = originalFuel - fuel;
            if(originalFuel < 0) break;
            // 0보다 크다면 목적지로 이동한 만큼의 연료의 2배를 충전
            originalFuel += (fuel * 2);
            // 운전자 위치를 목적지 위치로 바꿈
            driverPos = clients.get(minDisIdx).goalPos;
            // 승객 수를 하나 줄임 -> 리스트에서 제거
            clientNum--;
            clients.remove(minDisIdx);
            // 최소 거리, 최소 인덱스 초기화
            minDis = Integer.MAX_VALUE;
            minDisIdx = -1;
            if(clientNum == 0) {
                result = originalFuel;
            }
        }
        // 결과 출력
        System.out.println(result);
    }

    static int bfs(int[] startPos, int[] goalPos) {
        Queue<int[]> queue = new LinkedList<>();
        // 0번째 인덱스 : 출발 x좌표, 1번째 인덱스 : 출발 y좌표, 2번째 인덱스 : 거리
        int[] pos = {startPos[0], startPos[1], 0};
        boolean[][] visited = new boolean[N][N];
        queue.add(pos);
        // 출발 좌표 방문처리
        visited[startPos[0]][startPos[1]] = true;

        // 사방탐색
        int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}};

        while(!queue.isEmpty()){
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int dis = poll[2];

            // 좌표가 목적지와 같다면 그대로 거리를 반환하고 끝냄
            if (x == goalPos[0] && y == goalPos[1]) return dis;
            // 같지 않다면 사방탐색 시작
            for (int i = 0; i < 4; i++) {
                int dx = x + directions[i][0];
                int dy = y + directions[i][1];
                // 만약 지도의 범위를 벗어난다면 다음 사방탐색으로 이동
                if (dx < 0 || dx >= N || dy < 0 || dy >= N) continue;
                // 벽이 있다면 못 지나감
                if (map[dx][dy] == 1) continue;
                // 만약 방문했던 곳이라면 다음 사방탐색으로 이동
                if (visited[dx][dy]) continue;
                // 방문 체크
                visited[dx][dy] = true;
                // 큐에 해당 좌표랑 거리를 + 1해서 넣어줌
                queue.add(new int[] {dx, dy, dis+1});
            }
        }
        return -1;
    }
}
