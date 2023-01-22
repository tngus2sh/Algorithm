# [코드 비교]
```java
import java.io.IOException;

public class Main {
	public static void main(String args[]) throws IOException {
		//int b;
		int tmp;
		int[] data = new int[58]; 
		while((tmp = System.in.read()- (int)'A')>-1) {
			if(tmp>31)
				tmp = tmp-32;
			data[tmp]++;
		}
		
		tmp = 0;
		for(int i=1; i<58; i++) {
			if(data[tmp]<data[i]) {
				tmp = i;
			}
		}
		
		for(int i=0; i<58; i++) {
			if(data[tmp] == data[i] && i!=tmp) {
				System.out.println("?");
				return;
			}
		}
		System.out.println((char)(tmp+'A'));
	}
}

```
- 아이디 : play55, 메모리 : 11592KB, 시간 : 108ms, 코드 길이 : 518B

< 내 코드>
- 메모리 : 20040KB, 시간 : 264ms, 코드 길이 : 1540B

## 비교사항
- q11654에 정리된 System.in.read() 읽어볼 것!!!
- 그리고 나는 ?를 정의할 때 index라는 변수를 따로 만들어서 2이상이면 ?를 출력하도록 했는데 두번째부터 체크하고 싶으면 인덱스가 같은지 검사하면 된다!!
- max값을 비교할 때도 그 이전값과 비교해서 tmp 값에 max값이 들어가도록 비교만 하면 된다.. 

# [새로 알게 된 것]
1. 아스키 코드

## 아스키 코드
- 영문 알파벳을 사용하는 대표적인 문자 인코딩
- 많이 사용하는 아스키 코드
  - 숫자, 영문 대문자, 영문 소문자
  - ***숫자는 48~57, 영문 대문자는 65~90, 영문 소문자는 97~122***
- 변환
  - **문자 -> 숫자**
    ```java
    char ch = sc.nextLine().charAt(0);
    int num = (int)ch;
    ```
    - 입력 : a
    - 출력 : 97

  - **숫자 -> 문자** 
    ```java
    int num = sc.nextInt();
    char ch = (char)num;
    ```
    - 입력 : 65
    - 출력 : A
