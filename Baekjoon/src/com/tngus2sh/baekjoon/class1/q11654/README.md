# [코드 비교]
```java
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		int ch = System.in.read();
		System.out.println(ch);
	}
}
```
- 아이디 : riamoney92, 메모리 : 12824KB, 시간 : 68ms, 코드길이 : 170B


<내 코드>
- 메모리 : 17580KB, 시간 : 204ms, 코드길이 : 221B

## 비교사항
- 나는 Scanner에서 받아서 char로 받아들인 후 그 다음 int로 변환한 다음 아스키 코드를 출력하는 과정을 거쳤다.
- System.in에 대해 조금 더 공부!

# [정리]
## System.in.read()
- *아스키코드를 그대로 출력해줌*
- read() 메서드는 한 바이트씩 입력된 값을 읽어들이는 메서드
- 한 바이트씩 입력을 받기 때문에 한글은 입력받지 못한다.
- 영어, 숫자, 특수문자 등을 입력받을 수 있다.
- 아스키 코드에서 -48을 하면 숫자랑 같아져서 숫자정수로 받을 수 있다.
- char형으로 반환 문자열을 출력

### System.in.read(바이트 배열, StartByte, EndByte) 
- 바이트 배열로 받는 방법
- 바이트 배열을 받아 String으로 변환
- String으로 변환하면 나머지 배열의 값에서 공백부분이 쓰레기 데이터가 남으므로 trim()을 사용하여 쓰레기 데이터를 정리
```java
// System.in.read(바이트배열, StartByte, EndByte) -> 바이트배열로 입력받은 값을 집어넣습니다
// Byte배열 변수 선언
byte[] Byte = new byte[4096];
		
// Byte배열에 값을 입력
System.in.read(Byte, 0, 4096);
		
// Byte -> String전환
String StrByte = new String(Byte);
// 전환 시 Byte배열의 쓰레기데이터를 trim으로 정리
StrByte = StrByte.trim();
```