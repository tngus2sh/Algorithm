# [코드 비교]
```java
import java.io.*;
import java.util.Arrays;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int cnt = 0;
		while (T-- > 0) {
			String str = br.readLine();
			boolean[] ck = new boolean[26];
			boolean group = true;
			char old = str.charAt(0);
			ck[old-97] = true;
			for (int i = 1; i < str.length(); i++) {
				char nw = str.charAt(i);
				if (!ck[nw-97] && nw != old) {
					ck[nw-97] = true;
					old = str.charAt(i);
				}
				else if (ck[nw-97] && nw != old) {
					group = false;
					break;
				}

			}
			if (group) cnt++;
		}
		System.out.println(cnt);
	}
}
```
- 나는 str.charAt()을 String.valueOf를 사용해서 string으로 변환을 해주었다.
- 하지만, 이 위의 보기에서는 char로 받아와 아스키코드에서 97을 빼주어 인덱스처럼 만들어 배열에 넣어주었다.
- 그리고 각 인덱스 별로 해당 알파벳이 있으면 true or false인 boolean 타입으로 배열을 만들어 값을 판단했다.
- 또한 바로 이전 값인 경우에는 old에 값을 넣어 그것과 비교 판단을 해주었다.

# [새로 알게 된 것]
- StringBuilder에 toString()을 해주면 String처럼 메소드 사용 가능하다.