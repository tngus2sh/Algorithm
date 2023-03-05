import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 감시 / 골드4 / 1시간
 * https://www.acmicpc.net/problem/15683
 */
public class BJ_15683_감시 {
    static class Camera {
        int x;
        int y;
        int kind;
        int dNum;
        public Camera(int x, int y, int kind, int dNum) {
            this.x = x;
            this.y = y;
            this.kind = kind;
            this.dNum = dNum;
        }
    }
    static LinkedList<Camera> cameras;
    static final int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}}; // 오, 아, 왼, 위
    static int N, M, cameraNum, minRemainSpace;
    static int[] selected;
    static int[][] map;
    static boolean[][] isSpread;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 사무실의 세로 크기 : N, 가로 크기 : M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        // cctv 리스트
        cameras = new LinkedList<>();
        // 사각지대 최소 개수
        minRemainSpace = 0;

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());

                // CCTV 정보 담기
                if (map[n][m] > 0 && map[n][m] < 6) {
                    if(map[n][m] == 2) cameras.add(new Camera(n,m,map[n][m],2));
                    else if(map[n][m] == 5) cameras.add(new Camera(n,m,map[n][m],1));
                    else cameras.add(new Camera(n,m,map[n][m],4));
                }
                else if(map[n][m] != 6) minRemainSpace++;
            }
        }
        // 카메라 개수
        cameraNum = cameras.size();
        // 방향 고른 개수
        selected = new int[cameraNum];
        // 퍼진 부분인지 확인
        isSpread = new boolean[N][M];
        // 카메라가 하나라도 있으면 메소드 호출
        if(cameraNum > 0) recur(0, 0, cameras.get(0).dNum);
        System.out.println(minRemainSpace);
    }
    /** 카메라의 방향을 선택하는 메소드 */
    public static void recur(int n, int cnt, int dNum) { // n : 전체 카메라 중 몇번째, cnt : 방향 어디로 선택한지, dNum : 카메라 방향 개수
        if(cnt == 1) {
            // 전체 카메라의 방향을 다 뽑았다면 카메라 돌리기
            if(n >= cameraNum-1) {
                rollCameras();
            }
            // 그 다음 카메라 조정하러 ㄲ
            else {
                recur(n + 1, 0, cameras.get(n+1).dNum);
            }
            return;
        }

        for (int i = 0; i < dNum; i++) {
            selected[n] = i;
            recur(n, cnt + 1, dNum);
        }
    }
    /** 감시 카메라 돌아가는 메소드 */
    public static void rollCameras() {
        isSpread = new boolean[N][M];
        for (int i = 0; i < cameraNum; i++) {
            int x = cameras.get(i).x;
            int y = cameras.get(i).y;
            int kind = cameras.get(i).kind;
            int dir = selected[i];
            Stack<Integer> selectD = new Stack<>();
            // stack에 방향 집어넣기
            selectD = putStack(kind, dir, selectD);
            // 퍼지게 하기
            spread(x,y, selectD);
            // 남은 공간 수 구하기
            remainSpace();
        }

    }
    /** 기본 방향 이외에 카메라의 종류에 맞게 추가적으로 방향을 넣어주는 메소드 */
    public static Stack<Integer> putStack(int kind, int dir, Stack<Integer> selectD) {
        // 기본적으로 4방향중 한 방향은 들어가 있음
        selectD.push(dir);
        // 카메라가 2일 때는 반대방향도 넣어준다.
        if (kind == 2) selectD.push((dir+2)%4);
        // 카메라가 3일 때는 바로 옆방향을 넣어준다.
        else if(kind == 3) selectD.push((dir+1)%4);
        // 카메라가 4일 때는 연속으로 2개 방향을 더 넣어준다.
        else if(kind == 4) {
            selectD.push((dir+1)%4);
            selectD.push((dir+2)%4);
        }
        // 마지막으로 카메라 5일 때는 모든 방향이므로 모든 방향을 넣어준다.
        else if(kind == 5) {
            selectD.push((dir+1)%4);
            selectD.push((dir+2)%4);
            selectD.push((dir+3)%4);
        }
        return selectD;
    }
    /** 감시 카메라 방향 퍼지게 하는 메소드 */
    public static void spread(int x, int y, Stack<Integer> selectD) {
        // x, y : cctv 위치
        // selectD : 선택된 방향 배열, 예 : 3번 카메라 위, 오른쪽 방향이면은 0, 3이 들어가있음
        int l;
        while(!selectD.isEmpty()) {
            int d = selectD.pop();
            l = 1;
            while (true) {
                // 해당 방향으로 끝까지 퍼지게 한다.
                int dx = x + (l * directions[d][0]);
                int dy = y + (l * directions[d][1]);

                if (dx < 0 || dx >= N || dy < 0 || dy >= M) break;
                if (map[dx][dy] == 6) break;
                isSpread[dx][dy] = true;
                l++;
            }
        }
    }
    /** 남은 공간 세는 메소드 */
    public static void remainSpace() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0 && !isSpread[i][j]) count++;
            }
        }
        minRemainSpace = Math.min(count, minRemainSpace);
    }
}