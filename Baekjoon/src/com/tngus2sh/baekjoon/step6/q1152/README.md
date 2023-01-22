# [코드 비교]
```java
import java.io.IOException;
 
public class Main {
 
	public static void main(String[] args) throws IOException {
 
		int count = 0;
		int pre_str = 32;	// 공백을 의미한다.
		int str ;
		
		
		while(true) {
			str = System.in.read();
            
			// 입력받은 문자가 공백일 때,
			if(str == 32) {
				// 이전의 문자가 공백이 아니면
				if(pre_str != 32) count++;
			}
 
			// 입력받은 문자가 개행일때 ('\n')
			else if(str == 10) {
				// 이전의 문자가 공백이 아니면
				if(pre_str != 32) count++;
				break;
			}
			
			pre_str = str;
			
		}
		
		System.out.println(count);
	}
 
}
```
- 위 코드는 System.in.read()를 이용해서 입력받은 문자를 하나하나 가져와 문자가 공백일 떄를 비교를 한다.

<내 코드>
- 내 코드는 BufferReader와 StringTokenizer를 이용해서 한꺼번에 읽어들이고 Tokenizer로 한 문자씩 읽어들이고
- countTokens로 공백 기준으로 단어들의 개수를 알아낸다.