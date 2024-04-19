package Baekjoon.BFS;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 물통 / 골드2 / 55분 / 24.04.19
 */

public class BJ_14867_물통 {

    private static class Bottle {
        int A;
        int B;
        int cnt;
        public Bottle(int A, int B, int cnt) {
            this.A = A;
            this.B = B;
            this.cnt = cnt;
        }
    }

    private static int result;

    private static void bfs(int a, int b, int c, int d) {
        Set<String> visitedSet = new HashSet<>();
        visitedSet.add(0 + "_" + 0);

        Queue<Bottle> queue  = new LinkedList<>();
        queue.offer(new Bottle(0, 0, 0));

        while (!queue.isEmpty()) {
            Bottle now = queue.poll();

            if (now.A == c && now.B == d) {
                result = now.cnt;
                return;
            }

            // 1. 채우기
            if (!visitedSet.contains(a + "_" + now.B) && (now.A < a)) {
                visitedSet.add(a + "_" + now.B);
                queue.offer(new Bottle(a, now.B, now.cnt+1));
            }
            if (!visitedSet.contains(now.A + "_" + b) && (now.B < b)) {
                visitedSet.add(now.A + "_" + b);
                queue.offer(new Bottle(now.A, b, now.cnt+1));
            }

            // 2. 물 모두 버리기
            if (!visitedSet.contains(now.A + "_" + 0) && (now.B > 0)) {
                visitedSet.add(now.A + "_" + 0);
                queue.offer(new Bottle(now.A, 0, now.cnt+1));
            }
            if (!visitedSet.contains(0 + "_" + now.B) && (now.A > 0)) {
                visitedSet.add(0 + "_" + now.B);
                queue.offer(new Bottle(0, now.B, now.cnt+1));
            }

            // 3. 물 이동
            // 3-1. a->b
            if (now.A > 0) {
                if (now.A < (b - now.B)) {
                    if (!visitedSet.contains(0 + "_" + (now.A + now.B))) {
                        visitedSet.add(0 + "_" + (now.A + now.B));
                        queue.offer(new Bottle(0, now.A + now.B, now.cnt + 1));
                    }
                } else {
                    if (!visitedSet.contains((now.A - (b-now.B)) + "_" + now.B)) {
                        visitedSet.add((now.A - (b-now.B)) + "_" + now.B);
                        queue.offer(new Bottle(now.A - (b - now.B), b, now.cnt + 1));
                    }
                }
            }

            // 3-2. b->a
            if (now.B > 0) {
                if (now.B < (a - now.A)) {
                    if (!visitedSet.contains((now.A - (b-now.B)) + "_" + now.B)) {
                        visitedSet.add((now.A - (b-now.B)) + "_" + now.B);
                        queue.offer(new Bottle(now.A + now.B, 0, now.cnt + 1));
                    }
                } else {
                    if (!visitedSet.contains(a + "_" + (now.B - (a - now.A)))) {
                        visitedSet.add(a + "_" + (now.B - (a - now.A)));
                        queue.offer(new Bottle(a, now.B - (a - now.A), now.cnt + 1));
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken()); // A의 용량
        int b = Integer.parseInt(st.nextToken()); // B의 용량
        int c = Integer.parseInt(st.nextToken()); // A에 남겨야 하는 물의 용량
        int d = Integer.parseInt(st.nextToken()); // B에 남겨야 하는 물의 용량

        result = -1;

        bfs(a, b, c, d);

        System.out.println(result);

    }
}
