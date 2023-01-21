## [오류]
1. for문에서 T와 t부분에서 *unexpected token* 오류가 떴고, T는 *Unknown class: 'T'* 오류가 떴다.
```java
for(int t = 0; t < T; t++)
```
- 오류 원인 : 코드에 main 메소드가 없었다.
- 오류 해결 : main 추가

2. 런타임 오류(StringIndexOutOfBounds	: java.lang.StringIndexOutOfBoundsException)
   - 오류 해결 
     1. 문자열 공백 체크
     ```java
     // 문자열이 없을 때
     if(s.isEmpty()) {
         continue;
     } else {
     ```
     - 결론 : 해결 안됨
     - 해결 안된 이유 : 런타임 오류라고 하고 오류의 원인이 뭔지 제대로 파악을 안함 -> 내 멋대로 공백 문자열 때문이라고 생각하고 그에 해당하는 코드만 추가함
     
     2. for문 제한 값 수정
     - 수정 전
     ```java
      for (int i = 0; i < 3; i++) {
           for (int ii = 0; ii < loop; ii++) {
               char c = s.charAt(i);
               newS.append(c);     
      }
     ```
     - 수정 후
     ```java
      for (int i = 0; i < s.length(); i++) {
           for (int ii = 0; ii < loop; ii++) {
               char c = s.charAt(i);
               newS.append(c);
           }
      }
     ```
     - 결론 : 해결 됨
- 틀린 이유 : 문제 테스트 케이스로 제시된 ABC만 보았다. 그래서 문자열에서 문자 하나씩 떼오는 반복문을 3으로 제한을 두고 했기 때문에 그 이후에 /HTP와 같이 3을 벗어나는 문자열의 경우에는 위와 같은 오류가 난 것이다.

## [새로 알게된 것]
1. 문자열 붙이기 (이 방법으로 나는 String 문자열에 '+' 로 이어 붙이는 방법만 알고 있었다.)
2. 공백 체크
3. 공백 제거
 ### 1. StringBuffer (문자열 붙이기에서 사용한 방법)
- 쓰는 이유 : String 변수의 불변성
  - 한번 선언된 String 변수는 내용이 절대로 변하지 않는다. 내부적으로는 새로운 String 객체를 생성해 텍스트를 붙이도록 하고 있는 것!
  - ***String 임시 객체로 생성을 방지하고 메모리를 절약하기 위해 StringBuffer와 append()를 사용하는 것***
- 사용방법
```java
// 새로운 문자열
StringBuffer newS = new StringBuffer();

for(int i = 0; i < 3; i++) {
    for(int ii = 0; ii < loop; ii++) {
        char c = s.charAt(i);
        newS.append(c);
    }
}
```
- 생성
  - StringBuffer buff = new StringBuffer();
- 메소드
  - **append(넣고 싶은 값)** :  문자열을 붙여준다.
  - **insert(넣고 싶은 인덱스 번호, 넣고 싶은 값)** : 문자열 삽입
  - **delete(시작 번호, 끝 번호)** : 문자열 삭제
  - **deleteCharAt(지우고 싶은 번호)** : 문자 삭제

### 2. 공백체크 (isEmpty, length(), inBlank())
### 2-1. length() - Java 5이하
- 사용방법
  - 공백 제거 한 경우
    - ***str == null || str.trim().length == 0;***
    - length()로 길이가 0인지 확인
    - 공백 문자열도 빈 문자열로 체크함
    - 그 이후 자세한 사항은 isEmpty() 부분 참고
  - 공백 제거 안한 경우
    - *str == null || str.length == 0;*
    - 공백이 있는 문자열은 길이가 0이 아니므로, 빈 문자열로 체크되지 않음
### 2-2. isEmpty() - Java 6이상
- 사용방법
  - 공백 제거 한 경우
    -  ***str == null || str.trim().isEmpty();***
    - 먼저 문자열이 null인지 체크
    - 추가적으로 공백이 있는 문자열도 빈문자열로 판단하기 위해 *trim()* 메소드를 호출한다.
    - isEmpty() 메소드를 호출하기 이전에 trim()메소드를 호출해 문자열의 앞뒤 공백을 제거
    - 그 이후 java.lang.String 클래스의 isEmpty() 메소드를 호출하여 문자열의 길이가 0인지 체크
    - 공백이 있는 문자열도, 공백으로 체크되어 *true*를 리턴한다.
  - 공백 제거 안 한 경우
    - *str == null || str.isEmpty();*
    - 문자열의 길이를 체크해서 빈 문자열인지를 판단하기 때문에 공백이 있을 경우에는 isEmpty() 메소드는 *false*를 리턴한다.

### 2-3. isBlank() - Java 11이상
- 사용방법
  - ***str == null || str.isBlank();***
  - 빈 문자열이거나, 공백만으로 이루어진 문자열인 경우 *true*를 리턴한다.
  - 앞의 예제들처럼 trim() 메소드를 호출해 주지 않아도 됨!!

=> 하지만, 내가 쓰고 있는 현재 버전이 Java 8 이기 때문에 isBlank()는 사용하지 않고 isEmpty()사용

### 3. 공백 제거
### 3-1. trim()
- java.lang.String 클래스의 trim() 메소드는
- *앞뒤 공백을 제거한 문자열의 복사본을 리턴*
### 3-2. strip()
- Java 11 이후 새로 추가된 메소드
- java.lang.String 클래스
- 문자열 앞뒤의 공백을 모두 제거
### 3-2-1. stripLeading()
- 문자열 *앞*의 공백을 제거
### 3-2-2. stripTrailing()
- 문자열 *뒤*의 공백을 제거

### 3-3. trim() vs strip()
- trim() : '\u0020'(스페이스) 이하의 공백들만 제거 cf.'\u0009'(탭)
- strip() : 유니코드의 공백들을 모두 제거
- 예: 앞뒤로 공백 '\u2003'(EM SPACE)를 포함하면
  ```java
   String str = "\u2003Hello World!\u2003";
  ```
  이 공백은 '\u0020'이상의 공백문자이기 때문에 trim() 메소드로는 공백을 제거 할 수 없다. 따라서, strip()으로 제거해야한다.
