# 0B. 자바 8 람다와 인터페이스 스펙 변화
## 람다가 도입된 이유

기존의 CPU는 내부에 코어를 하나만 가지고 있었다.

빅데이터를 처리하기 위해 하나의 CPU안에 다수의 코어를 삽입하는 멀티 코어 프로세서들이 등장하면서 일반 프로그래머에게도 병렬화 프로그래밍에 대한 필요성이 생기기 시작했다.
![](https://i.imgur.com/woIxibO.png)

## 람다란 무엇인가?

**코드 블록**

- 코드 블록인 람다를 메서드의 인자나 반환값으로 사용할 수 있게 됨.

(인자 목록) → { 로직 }

- 한줄일 경우 { } 생략

## 함수형 인터페이스

- 추상 메서드를 하나만 갖는 인터페이스

## 메서드 호출 인자로 람다 사용

```java
public class B008 {
	public static void main(String[] args) {
		doIt(a -> a * a);
	}

	public static void doIt(MyFunctionalInterface mfi) {
		int b = mfi.runSomething(5);

		System.out.println(b);
	}
}
```

## 메서드 반환 값으로 람다 사용

```java
public class B009 {
	public static void main(String[] args) {
		MyFunctionalInterface mfi = todo();
		
		int result = mfi.runSomething(3);
		System.out.println(result);
	}
	
	public static MyFunctionalInterface todo() {
		return num -> num * num;
	}
}
```

## 컬렉션 스트림에서 람다 사용

```java
public class B013 {
	public static void main(String[] args) {
		Integer[] ages = { 20, 25, 18, 27, 30, 21, 17, 19, 34, 28 };
		Arrays.stream(ages)
		.filter(age -> age > 20)
		.forEach(age -> System.out.format("Age %d!!! Can't enter \n", age));
	}
}
```

## 메서드 레퍼런스 & 생성자 레퍼런스

- 인스턴스::인스턴스메서드
- 클래스::정적메서드
- 클래스::인스턴스메서드
- 클래스 :: new

## 인터페이스의 디폴트 메서드와 정적 메서드

- 정적 상수
- 추상 인스턴스 메서드
- **구체 인스턴스 메서드 - 디폴트 메서드**
- **(구체) 정적 메서드**

컬렉션 API를 강화하면서 컬렉션의 공통 조상인 Collection의 슈퍼 인터페이스인 Iterable 인터페이스에는 많은 변화가 필요했다.

새로운 추상 인스턴스 메서드를 추가하게 되면 기존에 해당 인터페이스를 구현한 모든 사용자 정의 클래스는 이를 추가적으로 구현해야만 한다. 

이는 자바 6이나 7로 만들어진 많은 프로그램들이 자바 8 기반 JVM에서 구동되지 않는 문제를 발생시킨다.

 따라서 해당 프로그램도 구동될 수 있게 디폴트 메서드라고 하는 개념을 인터페이스 스펙에 추가했다.