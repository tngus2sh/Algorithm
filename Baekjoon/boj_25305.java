import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 커트라인 / 브론즈2 / 5분
 * https://www.acmicpc.net/problem/25305
 */
public class q25305 {
    // Arrays.sort() 사용
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 응시자의 수
        int N = Integer.parseInt(st.nextToken());
        // 상을 받는 사람의 수
        int k = Integer.parseInt(st.nextToken());

        int[] student = new int[N];
        st = new StringTokenizer(br.readLine());
        // 각 학생의 점수
        for (int i = 0; i < N; i++){
            student[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(student);

        System.out.println(student[N-k]);
    }

    // 그냥 손수 정렬
    public static void main1(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 응시자의 수
        int N = Integer.parseInt(st.nextToken());
        // 상을 받는 사람의 수
        int k = Integer.parseInt(st.nextToken());

        int[] student = new int[N];
        st = new StringTokenizer(br.readLine());
        // 각 학생의 점수
        for (int i = 0; i < N; i++){
            student[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N-1 ; i++) {
            for(int j = i+1; j < N; j++) {
                if(student[i] > student[j]) {
                    int temp = student[j];
                    student[j] = student[i];
                    student[i] = temp;
                }
            }
        }

        System.out.println(student[N-k]);
    }
}
