package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 0만들기 / 골드 5 / 1시간
 * https://www.acmicpc.net/problem/7490
 */
public class BJ_7490_0만들기 {

    static StringBuilder str;
    // 연산자 배열
    static final char[] operator = {' ', '+', '-'};
    // 수식 담아두는 배열
    static char[] expression;
    static int N;
    public static void main(String[] args) throws IOException {
        str = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스 개수 : N
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T;t++) {
            // 자연수 N
            N = Integer.parseInt(br.readLine());

            // [초기화] 수식 담아두는 배열
            // 자연수 개수인 N개와 그 사이에 수식을 넣어줄 것 까지 합해서 총 2N - 1 의 크기인 배열 필요
            expression = new char[N + (N-1)];

            recur(0);

            str.append("\n");
        }
        System.out.println(str);
    }

    /** [재귀, 중복순열 사용] 수와 연산자를 넣어주는 메소드 */
    static void recur(int cnt) {
        // [종료조건] cnt의 값이 수식 개수 만큼이면 끝내기
        if(cnt == N-1) {
            // 마지막 숫자 넣어주기
            expression[(2 * cnt)] = Character.forDigit(cnt + 1, 10);
            // 합 계산
            sum();
            return;
        }

        for (char op : operator) {
            // 숫자 넣기
            expression[(2 * cnt)] = Character.forDigit(cnt + 1, 10);
            // 연산자 넣기
            expression[(2 * cnt) + 1] = op;
            recur(cnt + 1);
        }
    }

    /** 넣어진 수식으로 합 구하는 메소드 */
    static void sum() {
        StringBuilder sb = new StringBuilder();
        int size = 2 * N - 1;
        // 총합
        int totalSum = 0;
        // 이전의 값
        int frontNum = Integer.parseInt(String.valueOf(expression[0]));
        // 부호 비트 - + : 1, - : -1
        int sign = 1;
        // 공백이 앞에 있는지 체크
        boolean isBlank = false;

        sb.append(1);
        for (int i = 1; i < size; i++) {
            char exp = expression[i];
            sb.append(exp);
            // 문자가 숫자라면
            if(Character.isDigit(exp)) {
                int front = Integer.parseInt(String.valueOf(exp));
                // 앞에 공백이 있었을 때
                if(isBlank) {
                    frontNum = (frontNum*10) + front;
                    isBlank = false;
                    continue;
                }
                // 앞에 공백이 없었을 때
                frontNum = front;
            }
            // 문자가 숫자가 아니라면
            else {
                // 연산자가 공백일 때
                if(exp == ' ') {
                    isBlank = true;
                    continue;
                }
                // 이전의 연산자로 총합 갱신
                if(sign == 1) totalSum += frontNum;
                else totalSum -= frontNum;

                // 연산자가 + 일 때 부호 비트 갱신
                if(exp == '+') sign = 1;
                // 연산자가 - 일 때 부호 비트 갱신
                else sign = -1;
            }
        }

        // 이전의 연산자로 총합 갱신
        if(sign == 1) totalSum += frontNum;
        else totalSum -= frontNum;

        if (totalSum == 0) str.append(sb).append("\n");
    }
}