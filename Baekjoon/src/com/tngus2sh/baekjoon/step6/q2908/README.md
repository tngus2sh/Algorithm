# [코드 비교]
```java
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        int A = 0;
        int B = 0;

        A += System.in.read() - 48;
        A += (System.in.read() - 48) * 10;
        A += (System.in.read() - 48) * 100;
        System.in.read();
        B += System.in.read() - 48;
        B += (System.in.read() - 48) * 10;
        B += (System.in.read() - 48) * 100;

        System.out.println(Math.max(A, B));
    }
}
```
- System.in.read()로 한 바이트씩 읽어오고 그걸 역순으로 다시 받아들이는 방법
- 내 코드보다 더 효과적으로 보인다.