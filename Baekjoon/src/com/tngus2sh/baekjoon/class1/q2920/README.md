# [코드 비교]
- 아이디 : dks301, 메모리 : 12804KB, 시간 : 64ms, 언어 : java 8, 코드 길이 : 484B
```java
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String ascending = "1 2 3 4 5 6 7 8";
		String descending= "8 7 6 5 4 3 2 1";
		String output = input.equals(ascending) ? 
				"ascending" : (input.equals(descending) ? "descending" : "mixed");
		System.out.println(output);
	}
}
```
- 내 코드
- 메모리 : 17644KB, 시간 : 204ms, 언어 : java 11, 코드길이 : 1190B

## 비교사항
- 문제 분석
  - 문제에서 1~8까지의 음계가 있다고 했고, 입력으로는 1~8까지 숫자가 한 번씩 등장한다고 했다.
  - 그렇다면 *ascending*에 해당하는 건 1,2,3,4,5,6,7,8 밖에 없고,
  - *descending*에 해당하는 건 8,7,6,5,4,3,2,1 밖에 없기 때문에
  - 경우는 *3개*로 나누면 됨


- 나의 문제 사항
  - 문제를 제대로 읽지 않고, 숫자가 중복되는 상황까지 넣었다.
  - 또한, ascending과 descending에 해당하는 사항이 각각 한 번 밖에 없는데 쓸데없이 이전 값과 비교하는 구문을 넣었다.
  - BufferedReader와 InputStreamReader, 축약해서 하는 ? 이런 구문에 대한 지식이 없다.

# [새로 알게 된 것]
1. InputStreamReader
2. BufferedReader
3. parseInt 
4. ? 구문 - 삼항연산자

## 1. InputStreamReader
- stream이란?
  - 출발지와 도착지를 이어주는 빨대, 데이터의 흐름
  - 입력장치 -(스트림)- 프로그램 -(스트림)- 출력장치
  - 단 방향이기 때문에 입력과 출력이 동시에 이뤄질 수 없다. 따라서 용도에 따라 입력 스트림, 출력 스트림 두가지로 나뉜다.
  - 자바에서 가장 기본이 되는 입력 스트림 : *InputStream* , 출력 스트림 : *OutputStream*
  - System.in이 InputStream 타입의 필드
  - InputStream inputstream = System.in;


- **InputStreamReader**
  - 문자 스트림 
  - InputStream은 우리가 InputStream.read()를 통해 입력받으려고 해도 1Byte 만 인식하니 한글은 입력해봤자 읽지 못하고 이상한 문자만 나오게 된다.
  - 문자를 온전하게 받아들이기 위해 *InputStreamReader*를 사용한다.
  - 따라서, InputStream의 바이트 단위로 읽어들이는 형식을 문자 단위(character)로 데이터로 변환시키는 중개자 역할
  - InputStream inputstream = System.in;
  - InputStreamReader sr = new InputStreamReader(inputstream);
  
## 2. BufferedReader / BufferedWriter
**버퍼의 이점**
- 이전 Scanner 방식(버퍼를 사용하지 않는 입력) : 키보드의 입력이 키를 누르는 즉시 바로 연결된다.
- 버퍼를 사용하는 입력 : 키보드의 입력이 있을 때마다 한 문자씩 버퍼로 전송. 버퍼가 가득 차거나, 개행 문자가 나타나면, 버퍼의 내용을 한 번에 전송함
- 하드디스크는 원래 속도가 엄청 느리다. 하드 뿐만 아니라 키보드나 모니터와 같이 외부 장치와의 데이터 입출력은 시간이 걸리는 작업.
- 그냥 전송하게 되면 CPU와 성능 갭이 많이 나 비효율적

    => **입력이 들어올 때마다 데이터를 보내는 것보다 한번에 모아서 이동시키는 것이 효율적이고 빠르다.**
### 2-1. BufferdReader
- 버퍼를 이용한 입력
- 엔터만 경계로 인식하고 받은 데이터가 *String*으로 고정 -> 따라서, 데이터를 따로 가공해야함
- 사용방법
  - import java.io.BufferedReader
```java
try{// 예외처리 필수 또는 throwsIOException해주기
    // 콘솔에서 입력받을 경우
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    
    // 파일에서 입력받을 경우
    FileReader fr=new FileReader("BufferedReaderEx1.java");
    BufferedReader br_f=new BufferedReader(fr);
    
    // String이 리턴값이라 형변환 필수, 라인단위
    int num = Integer.parseInt(br.readLine());
    br.close(); // 입출력이 끝난 후 달아주기
    
    // 파일의 한줄 한 줄 읽어서 출력
    String line="";
    for(int i=1;(line=br_f.readLine())!=null;i++){
    System.out.println(line);
    }
} catch(IOException e) {
    e.printStackTrace();
    System.out.println(e.getMessage());
}
```
- **BufferedReader br = new BufferedReader(new InputStreamReader(System.in));**

  => 이걸 풀어 쓰면
  
  => Inputstream inputstream = System.in;
  
  => InputStreamReader sr = new InputStreamReader(inputstream);
  
  => BufferedReader br = new BufferedReader(sr);
### 2-2. BufferedWriter
- System.out.println("");과 동일하게 사용가능
- 많은 양의 출력이 필요할 떄 이용
- 사용방법
  - import java.io.BufferedWriter
```java
BufferedWriter bw = new BufferedWriter(new FileWriter("bufferedWriter.txt"));
bw.write("hello\n"); // 출력
bw.newLine(); // 개행 즉 엔터 역할
bw.write("I am writing"); // 개행과 함께 출력
bw.flush(); // 남아 있는 데이터를 모두 출력
bw.close() // 스트림 닫아줌
```
  - 메소드가 문자열 출력과 개행을 동시에 해주지 않기 때문에 개행을 하려면 write 에 "\n"을 넣어주거나 newLine 함수를 사용해 주어야 한다.
  - 다 사용한 뒤 *flush()* 함수를 써서 버퍼에 남아있는 데이터를 출력해 없앤 후, 스트림을 닫아준다.

cf.

버퍼(Buffer)
- 데이터를 한 곳에서 다른 곳으로 전송하는 동안 일시적으로 그 데이터를 보관하는 임시 메모리 영역
- 입출력 속도 향상을 위해 버퍼 사용

버퍼 플러시(buffer flush)
- 버퍼에 남아있는 데이터를 출력시킴
- 버퍼를 비우는 동작

## 3. parseInt
- parse의 종류
  - **int**, byte, short, long, float, double 등 숫자와 관련된 타입은 전부 가능
  - parseByte(), parseShort(), parseInt(), parseLong(), parseFloat(), parseDouble()
  - boolean은 없고, char는 charAt()
  

- **Integer.parseInt(String s)**
  - 문자열을 숫자로 변환시킴
  - parseInt 메소드는 Integer 클래스의 static 함수
  - *정수형으로 바뀌는 함수이기 때문에 문자열에 문자가 들어간 String은 바꿀 수 없음 [컴파일 오류]*
- **Integer.parseInt(String s, int radix)**
  - 숫자형의 문자열을 첫번째 인자 값(String s) 으로 받고, 변환할 진수값(int radix)을 입력하면 해당 진수에 맞춰 Integer 형으로 변환
  - 예: Integer.parseInt("1004", 8) -> 8진수로 변환

## 4. ? 구문 - 삼항 연산자
- 기본 형태
  - String a = [조건문] ? [참일때 값] : [거짓일 때 값]
- 주의
  - 가독성이 좋아진다고 컴파일의 속도가 빨라지는 것은 아님
  - 조건 결과에 따른 실행 로직이 복잡한 경우에 삼항 연산자를 사용하면 가독성을 더 떨어뜨릴 수 있다.
  - 삼항 연산자를 중복하여 사용하지 않아야 한다.

*참고*
https://st-lab.tistory.com/41