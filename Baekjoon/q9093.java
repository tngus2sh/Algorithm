package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 단어 뒤집기 / 브론즈 1 / 10분
 * https://www.acmicpc.net/problem/9093
 */
public class q9093 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        StringTokenizer st ;
        StringBuilder sb;
        for(int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            sb = new StringBuilder();
            while(st.hasMoreTokens()) {
                String s = st.nextToken();
                for (int i = s.length() - 1; i >= 0; i--) {
                    sb.append(s.charAt(i));
                }
                sb.append(" ");
            }
            System.out.println(sb);
        }
        
    }
}

