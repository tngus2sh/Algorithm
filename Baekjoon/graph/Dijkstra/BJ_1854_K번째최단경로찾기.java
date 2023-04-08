import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1854_K번째최단경로찾기 {
    static final int INF = Integer.MAX_VALUE;
    static class Node {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        // 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 도시들의 개수 : n, 도시간에 존재하는 도로의 수 : m, k번째 최단경로
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }
        // (a,b,c) a->b : c
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b,c));
        }
        int start = 1;

        PriorityQueue<Integer>[] dist = new PriorityQueue[n + 1];
        for (int i = 0; i < n+1; i++) {
            dist[i] = new PriorityQueue<>((o1, o2) -> {return o2 - o1;});
        }

        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> {return o1.cost - o2.cost;});
        q.add(new Node(1,0));
        dist[start].add(0);

        while (!q.isEmpty()) {
            Node curNode = q.poll();

            for (int i = 0; i < graph.get(curNode.idx).size(); i++) {
                Node nxtNode = graph.get(curNode.idx).get(i);

                if (dist[nxtNode.idx].size() < k) {
                    dist[nxtNode.idx].add(curNode.cost + nxtNode.cost);
                    q.add(new Node(nxtNode.idx, curNode.cost + nxtNode.cost));
                } else if (dist[nxtNode.idx].peek() > curNode.cost + nxtNode.cost) {
                    dist[nxtNode.idx].poll();
                    dist[nxtNode.idx].add(curNode.cost + nxtNode.cost);
                    q.add(new Node(nxtNode.idx, curNode.cost + nxtNode.cost));
                }
            }
        }


        for (int i = 1; i < n+1; i++) {
            if (dist[i].size() == k) {
                bw.write(dist[i].peek() + "\n");
            } else {
                bw.write(-1 + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
