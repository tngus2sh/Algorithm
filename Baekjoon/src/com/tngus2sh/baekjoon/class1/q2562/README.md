# [틀림]
틀린 점 : 최대값은 잘 찾았지만 최대값이 몇번째인지를 찾는 부분에서 틀렸다.

틀린 이유 : 
```java
for(int i = 0; i < 9; i++) {
    N = sc.nextInt();
    max = Math.max(N, max);
    maxindex = i;
}
```
- 처음에는 위와 같이 Math.max로 최댓값을 비교해서 최대 값이 나오면 그 값을 집어넣는 방식으로 갱신을 했다.
- 그리고 *'max값을 집어넣을 때 index값을 넣으면 그게 maxIndex값이라고 생각'* 을 했다.
- **하지만 결론적으로는 for문을 돌 때마다 i값을 집어넣는 격이다.**
```java
for(int i =0; i < 9; i++) {
    N = sc.nextInt();
    if(N >= max) {
        max = N;
        maxIndex = i;
    }
}
```
- 따라서, 위의 식과 같이 if문으로 max값일 때 값을 집어넣는 방법으로 바꾸었다.