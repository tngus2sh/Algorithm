# [새로 알게된 것]
1. StringTokenizer

## StringTokenizer
- BufferedReader 클래스나 문자열을 분리하기 위해 사용하는 것
- 하나의 문자열을 여러 개의 토큰으로 분리하는 클래스
- import java.util.StringTokenizer
- 생성자 
  - **StringTokenizer st = new StringTokenizer(문자열);**
    - 띄어쓰기 기준으로 문자열을 분리
  - **StringTokenizer st = new StringTokenizer(문자열, 구분자);**
    - 구분자 기준으로 문자열 분리
  - **StringTokenizer st = new StringTokenizer(문자열, 구분자, true/false);**
    - 구분자를 기준으로 문자열을 분리할 때 구분자도 토큰으로 넣을지(true), 구분자는 분리된 문자열 토큰에 포함 안시킬지(false)
    - 디폴트는 false
- 사용방법
```java
StringTokenizer st = new StringTokenizer(str);

System.out.println(st.nextToken());
System.out.println(st.nextToken());
System.out.println(st.nextToken());
```
- 메서드
  - hasMoreTokens() : boolean
    - 남아있는 토큰이 있으면 true를 리턴, 더 이상 토큰이 없으면 false를 리턴
  - nextToken() : String
    - 객체에서 다음 토큰을 반환
  - nextToken(String delim) : String
    - delim 기준으로 다음 토큰을 반환
  - countTokens() : int
    - 총 토큰의 개수를 리턴