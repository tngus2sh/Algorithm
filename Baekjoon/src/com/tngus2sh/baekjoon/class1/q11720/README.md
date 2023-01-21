# [정리]
## String.valueOf()
- 파라미터가 null이면 문자열 "null"을 만들어 반환
- null 값 체크 방법
  - "null".equals(String) 으로 체크
- vs toString()
  - toString()은 대상 값이 null이면 NPE(NullPointerError)를 발생시키고 Object에 담긴 값이 String이 아니여도 출력

=>여기서는 parseInt안에 문자열 값이 들어가야하므로 charAt을 문자열로 바꿔줘야 하는 경우가 생김 따라서, 위의 메소드를 사용