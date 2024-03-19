package Baekjoon.Simulation;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 / 티어 / 걸린 시간 / 푼 날짜
 * 틱택토 / 골드5 / 40분 / 24.03.19
 */

public class BJ_7682_틱택토 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        StringBuilder sb = new StringBuilder();

        while (!(input = br.readLine()).equals("end")) {

            char[][] map = new char[3][3];

            int cntX = 0;
            int cntO = 0;

            for (int i = 0; i < 9; i++) {
                map[i/3][i%3] = input.charAt(i);

                if (map[i/3][i%3] == 'X') cntX++;
                else if (map[i/3][i%3] == 'O') cntO++;
            }

            // 1. X값과 O의 차이가 2이상 나고 X가 O보다 크거나 작을 때 invalid
            if (!((cntX == cntO) || (cntX == cntO+1))) {
                sb.append("invalid").append("\n");
                continue;
            }

            boolean bingoX = false;
            boolean bingoO = false;
            // 2. 가로 탐색
            for (int i = 0; i < 3; i++) {
                int initial = map[i][0];

                if (initial == '.') continue;

                boolean isBingo = false;
                for (int j = 1; j < 3; j++) {
                    if (map[i][j] != initial) break;
                    if (j==2) isBingo = true;
                }

                if (isBingo) {
                    if (initial == 'X') bingoX = true;
                    else if (initial == 'O') bingoO = true;
                }
            }

            // 3. 세로 탐색
            for (int i = 0; i < 3; i++) {
                int initial = map[0][i];

                if (initial == '.') continue;

                boolean isBingo = false;
                for (int j = 1; j < 3; j++) {
                    if (map[j][i] != initial) break;
                    if (j==2) isBingo = true;
                }

                if (isBingo) {
                    if (initial == 'X') bingoX = true;
                    else if (initial == 'O') bingoO = true;
                }
            }

            // 4. 대각선 탐색
            // 4-1. 왼쪽 대각선
            int initial = map[0][0];
            boolean isBingo = false;
            if (initial != '.') {
                for (int i = 1; i < 3; i++) {
                    if (map[i][i] != initial) break;
                    if (i == 2) isBingo = true;
                }
            }

            if (isBingo) {
                if (initial == 'X') bingoX = true;
                else if (initial == 'O') bingoO = true;
            }

            // 4-2. 오른쪽 대각선
            initial = map[0][2];
            isBingo = false;
            if (initial != '.') {
                for (int i = 1; i < 3; i++) {
                    if (map[i][2 - i] != initial) break;
                    if (i == 2) isBingo = true;
                }
            }

            if (isBingo) {
                if (initial == 'X') bingoX = true;
                else if (initial == 'O') bingoO = true;
            }

            // 5. 빙고를 X가 완성했는데 O도 완성했을 때 -> invalid
            if (bingoX && bingoO) {
                sb.append("invalid").append("\n");
            }

            // 6. 빙고를 X가 완성했는데 X와 O의 값이 같을 때 -> invalid
            else if (bingoX && (cntX == cntO)) {
                sb.append("invalid").append("\n");
            }

            // 7. 빙고를 O가 완성했는데 X값이 O값보다 많을 때 -> invalid
            else if (bingoO && (cntX == (cntO+1))) {
                sb.append("invalid").append("\n");
            }

            // 8. 위 invalid 영역을 지나서 둘 중에 하나가 빙고를 완성했다면 -> valid
            else if (bingoX || bingoO){
                sb.append("valid").append("\n");
            }

            // 9. 둘의 차이가 1나고 X가 O보다 크거나 같을 때 둘의 합이 격자판의 크기(9)와 같다면 valid
            else if(cntX + cntO == 9) {
                sb.append("valid").append("\n");
            }

            else {
                sb.append("invalid").append("\n");
            }

        }

        System.out.print(sb);

    }
}

