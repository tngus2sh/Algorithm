# [코드 비교]
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			String input = br.readLine().trim();

			int total = 0;
			int cumulative = 1;
			for (int k = 0; k < input.length(); k++) {
				if (input.charAt(k) == 'O') {
					total += (cumulative++);
				} else {
					cumulative = 1;
				}
			}
			sb.append(total + "\n");
		}
		System.out.println(sb);
	}
}
```
- 아이디 : notoonull, 메모리 : 13052KB, 시간 : 68ms, 코드길이 : 645B

<내 코드>
- 메모리 : 14276KB, 시간 : 136ms, 코드길이 : 1348B

## 비교사항
- StringBuilder 모름
- trim()으로 공백제거 안함
- 난 배열 사용
- ***그래도 BufferedReader 쓰면서 했다 :) 공부한거 써먹음!!***

# [오류]
## 런타임에러(NullPointer)
- 오류 원인 
```java
Scanner sc = new Scanner(System.in);
int T = sc.nextInt();

for(int t = T; t < T; t++) {
    BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    String oxs = buf.readLine();
}
```
- Scanner로 T값을 받고, 그 다음 BufferedReader를 사용해서 String을 받아왔다.
- *Scanner에서 nextInt()는 개행문자(엔터, newline)을 제거하지 않는다.*
- Scanner로 받은 후에 개행문자 전까지 값을 받아온다. 따라서, BufferedReader를 받을 때는 개행문자를 기준으로 값을 받아오므로
- T\n -> Scanner가 T만 받아옴 -> \n"OXOX" -> BufferedReader(개행문자 전까지) -> null\n => [오류]
- 개행문자 전인 null값을 받아와 NullPointer 오류가 발생한 것이다.


- 오류 해결
```java
BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
int T = Integer.parseInt(buf.readLine());

for(int t = T; t < T; t++) {
    String oxs = buf.readLine();
}
```
- 해결방법
  1. nextInt() 이후에 nextLine()으로 한 줄을 받아들여 개행을 처리
     - nextLine()은 한 줄 즉, 개행문자(\n)까지 읽은 후 개행문자를 버린 나머지만 리턴한다. 
  2. 걍 처음부터 BufferedReader로 처리
- 나는 2번으로 전체 입력 값을 BufferedReader로 받아들여왔다.

# [새로 알게된 것]
1. StringBuilder

## StringBuilder
- 문자열이 1개이상 있어서 이 문자열들을 더할 때 사용
- 쓰이는 이유
  - String 객체끼리 더하는 방법은 메모리 할당과 해제를 발생시키는데 덧셈 연산이 많아지면 성능적으로 안 좋아짐
  - java에서 String 객체는 변경 불가능해 한번 생성되면 내용을 바꿀 수 없다.
  - 따라서 하나의 문자열을 다른 문자열과 연결하면 새 문자열이 생성되고, 이전 문자열은 가비지 컬렉터로 들어간다.
- 사용방법
  - StringBuilder의 객체를 생성한 후, append()의 인자로 연결하고자 하는 문자열을 넣어서 StringBuilder의 객체를 통해 호출한다.
  - 그리고 출력 시에는 toString()을 붙여야 하고, String 변수에 넣을 때도 마찬가지이다.
```java
StringBuilder stringBuilder = new StringBuilder();
stringBuilder.append("문자열 ").append("연결");
// String str = stringBuilder;   // String에 StringBuilder를 그대로 넣을 순 없다. toString()을 붙여야 한다
String str = stringBuilder.toString();
// 두 println()은 같은 값을 출력한다
System.out.println(stringBuilder);
System.out.println(str);
```