package BackTracking;

import java.util.LinkedList;
import java.util.Scanner;

public class BJ_2661_좋은수열 {
	// 부분집합을 위한 선택 체크
	static boolean[] setCheck;
	// 부분집합 담아두는 리스트
	static LinkedList<String> subset;
	// 조합을 위한 선택 체크
	static boolean[] combinationCheck;
	
	static String str;
	static int strLen;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 길이 : N
		int N = sc.nextInt();
		
		setCheck = new boolean[3];
		subset = new LinkedList<>();
		
		pickSet(0);
		
		sc.close();
	}
	
	static void pickSet(int cnt) {
		if(cnt == 3) {
			makeSequence();
			return;
		}
		
		setCheck[cnt] = true;
		pickSet(cnt + 1);
		setCheck[cnt] = false;
		pickSet(cnt + 1);
	}
	
	/** 수열 만들어줌 */
	static void makeSequence() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 3; i++) {
			if(setCheck[i]) {
				sb.append(i);
			}
		}
		
//		if(sb.toString().length() > 0) {
//			subset.add(sb.toString());
//			str = sb.toString();
//		}
		str = sb.toString();
		
		System.out.println(str);
		
		// 만들어진 부분집합들로 조합 만듦
		combinationCheck = new boolean[subset.size()];
		strLen = str.length();
		makeCombination(0);
		
	}
	
	static void makeCombination(int cnt) {
		if(cnt == strLen) {
			check();
			return;
		}
		
		for (int i = 0; i < strLen; i++) {
			if(!combinationCheck[i]) {
				combinationCheck[i] = true;
				makeCombination(cnt + 1);
			}
		}
	}
	
	static void check() {
		for (int i = 0; i < strLen; i++) {
			if(combinationCheck[i]) {
				System.out.print(str.charAt(i));
			}
		}
		System.out.println();
	}
}
