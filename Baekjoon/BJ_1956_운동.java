import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1956_운동 {
    static class Road {
        int town;
        int road;

        Road(int town, int road) {
            this.town = town;
            this.road = road;
        }

        // -- debug
        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Road{");
            sb.append("town=").append(town);
            sb.append(", road=").append(road);
            sb.append('}');
            return sb.toString();
        }
    }
    static int V, minDis;
    static HashMap<Integer, LinkedList<Road>> connection;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // v개의 마을, E개의 도로
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 마을간의 도로 연결 변수
        connection = new HashMap<>();

        // a번 마을에서 b번 마을로 가는 거리가 c인 도로
        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(!connection.containsKey(a)) {
                connection.put(a, new LinkedList<>());
            }
            connection.get(a).add(new Road(b,c));
        }

        // --debug
//        System.out.println(connection.toString());

        minDis = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            int res = bfs(i);

            minDis = Math.min(res, minDis);
        }

        // 만약 운동 경로를 찾는 것이 불가능하다면 -1 출력 -> minDis == Integer.MAX_VALUE;
        if(minDis == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minDis);
        }
    }

    static int bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();

        // 시작 위치, 거리
        int[] startArr = {start, 0};
        queue.add(startArr);

        // 거쳐갔는지 표시
        boolean[] visited = new boolean[V+1];

        while(!queue.isEmpty()) {
            int[] poll = queue.poll();
            int pos = poll[0];
            int dis = poll[1];

            // 이미 거리가 최소 거리보다 크면 그냥 return
            if(dis > minDis) return Integer.MAX_VALUE;
            // 시작위치로 되돌아 왔을 때 (시작위치이고 거리가 0보다 크면 돌아왔다는 뜻이니까)
            if(pos == start && dis > 0) return dis;

            // 이미 거쳐갔던 곳이면은 패쓰~~
//            if(visited[pos]) continue;

            // 출발하는 방향이 아닌 경우 == hashmap에서 key값이 아닌 경우 그 다음으로 넘어가기
            if(!connection.containsKey(pos)) continue;

            visited[pos] = true;
            for (int i = 0; i < connection.get(pos).size(); i++) {
                int nextTown = connection.get(pos).get(i).town;
                int nextDis = connection.get(pos).get(i).road;
                nextDis += dis;
                queue.add(new int[] {nextTown, nextDis});
            }
        }

        return Integer.MAX_VALUE;
    }
}
