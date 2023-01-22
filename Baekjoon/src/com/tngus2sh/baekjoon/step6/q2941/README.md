# [새로 알게 된 것]
1. replace, replaceAll, replaceFirst
## replace()
- **String.replace(char, char)**
  - 첫번째 인자의 문자를 찾고, 두번째 문자로 변환해 줍니다.
- **String.replaceAll(String, String)**
  - 첫번째 문자열을 찾고, 두번째 문자열로 변환해 줍니다.
- **String.replaceFirst(String, String)**
  - 첫번째 인자의 문자를 찾고, 두번째 문자로 변환해 줍니다. 단, 한번만 변환해줍니다.


- 이런식으로 replace는 *공백을 제거*하는데에도 사용 가능
```java
s = s.replaceAll(" ", ""); // " "의 공백을 갖고 있는 문자열을 모두 공백 제거
s = s.replaceFirst(" ", "")  // " "의 공백을 갖고 있는 문자열 중 첫번째 문자열을 공백 제거
```