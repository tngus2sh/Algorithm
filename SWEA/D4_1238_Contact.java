import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class D4_1238_Contact {

    static HashMap<Integer, LinkedList<Integer>> adjacent;
    // 연락인원
    static int size = 100+1;
    static HashMap<Integer, Boolean> visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 1; t <= 10 ; t++) {
            st = new StringTokenizer(br.readLine());
            // 데이터의 길이 : N
            int N = Integer.parseInt(st.nextToken());
            // 시작점 : start
            int start = Integer.parseInt(st.nextToken());

            adjacent = new HashMap<>();
            visited = new HashMap<>();

            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N-1; n+=2) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                // from이 처음으로 들어온거라면 생성해줌
                if(!adjacent.containsKey(from)) {
                    adjacent.put(from, new LinkedList<>());
                    adjacent.get(from).add(to);
                }
                // 이전에 입력된 to가 아니라면 값을 넣는다.
                else {
                    if (!adjacent.get(from).contains(to)) {
                        adjacent.get(from).add(to);
                    }
                }
                // 방문 체크도 똑같이 초기화 해줌
                if(!visited.containsKey(from)) {
                    visited.put(from, false);
                }
                if (!visited.containsKey(to)) {
                    visited.put(to, false);
                }
            }
            
            System.out.print("#" + t + " ");
            bfs(start);
        }
    }

    static void bfs(int point) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(point);
        visited.put(point, true);
        int max  = 0;
        ArrayList<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            max = 0;

            // 같은 깊이에서 반복해 최댓값 찾기
            for (int t = 0; t < queueSize; t++) {
                int poll = queue.poll();

                if(adjacent.containsKey(poll)) {
	                for (int i = 0; i < adjacent.get(poll).size(); i++) {
	                    int temp = adjacent.get(poll).get(i);
	                    if(!visited.get(temp)) {
	                        visited.put(temp, true);
	                        queue.offer(temp);
	                        max = Math.max(max, temp);
	                    }
	                }
                }
            }
            list.add(max);
        }

        // 최대 레벨의 최댓값 출력
        System.out.println(list.get(list.size()-2));
    }
}