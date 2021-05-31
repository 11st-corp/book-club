# ch03. 자바와 객체 지향
## 객체 지향은 인간 지향이다

구조적 프로그래밍: Divide & Conquer -> 함수
객체 지향 프로그래밍: 기계에 맞춰 사고하던 방식을 버리고 현실세계를 인지하는 방식

## 객체 지향의 4대 특성

### 추상화

- 공통 특성 / 공통 속성 추출
- OOP의 추상화는 모델링
- **모델링**은 실제 사물을 정확히 복제하는 게 아닌 목적에 맞게 관심있는 특성만을 추출해서 표현하는 것
- 추상화는 주관적이므로 결과가 설계자마다 다를 수 있다.

### 추상화와 메모리

```
public class MouseDriver {
    public static void main(String[] args) {
        Mouse mickey = new Mouse(); // 1
        mickey.name = "미키";
        mickey.age = 85;
        mickey.countOfTail = 1;

        mickey.sing();

        mickey = null; // 2

    }
}

```

1. Mouse 객체에 대한 참조 변수 mickey를 만들고,
 Mouse 클래스의 인스턴스를 하나 만들어 힙에 배치
Mouse 객체에 대한 주소를 참조 변수 mickey에 할당
2. 가비지 컬렉터가 아무도 참조해주지 않는 Mouse 객체를 수거한다.
Mouse 객체가 사용했던 힙 영역의 메모리 공간을 뺀다.

## static

**정적 메서드**는 객체들의 존재 여부에 관계없이 쓸 수 있는 메서드

JVM 구동시 스태틱 영역에 바로 배치되기에 객체의 존재 여부에 관계없이 쓸 수 있다.

공유변수이므로 별도로 초기화를 해주지 않아도 기본값으로 초기화가 된다.

클래스=정적=스태틱

객체=오브젝트=인스턴스

### 상속: 재사용 + 확장

```java
public class 동물 {
	String myClass;
	
	동물() {
	myClass = "동물";	
	} 
	
	void showMe() {
		System.out.print(myClass);
	}
}
```

```java
public class 조류 extends 동물 {
	조류() {
		myClass = "조류";
	}
}
```

하위 클래스에서 showMe 메서드를 다시 작성하지 않아도 해당 메서드를 호출할 수 있다.

- is a kind of 관계를 만족한다.
- 인터페이스는 클래스가 '무엇을 할 수 있다'라고 하는 기능을 구현하도록 강제한다.
- 상위 클래스는 물려줄 특성이 풍성할 수록 좋고, 인터페이스는 구현을 강제할 메서드의 개수가 적을 수록 좋다.

### 상속과 메모리

```java
public class Animal {
    public String name;

    public void showName() {
        System.out.printf("안녕, 나는 %s야, 반가워 \\n", name);
    }
}

```

```java
public class Penguin extends Animal {
    public String habitat;

    public void showHabitat() {
        System.out.printf("%s는 %s에 살아\\n", name, habitat);
    }
}

```

```java
public class Driver {
    public static void main(String[] args) {
        Penguin pororo = new Penguin(); // 1

        pororo.name = "뽀로로";
        pororo.habitat = "남극";

        pororo.showName();
        pororo.showHabitat();

        Animal pingu = new Penguin(); // 2

        pingu.name = "핑구";
        pingu.habitat = "EBS";

        pingu.showName();
    }
}

```

- 클래스의 인스턴스만 힙영역에 생기는 게 아니라 상위 클래스의 인스턴스도 함께 생성된다.

### 암묵적 형변환

- 상위 클래스 타입 = 하위 클래스 타입
- ``Animal pingu = new Penguin();``
- 형변환된 상위 클래스 변수가 사용할 수 있는 범위는 자신 클래스에 정의된 변수와 메소드만 사용이 가능
- 단, 상위 클래스의 메소드를 하위클래스에서 오버라이딩 했을 경우 하위클래스에 선언된 메소드가 호출됨
- pingu는 showHabitat 메서드를 사용할 수 없다.

### 명시적 형변환

- ``Penguin pororo = (Penguin)pingu;``
- 상위클래스 타입 자리에 올 수 있는 객체는 실제 가리키는 메모리가 하위 클래스 타입이어야 가능

### 다형성: 사용편의성

**오버라이딩**

상위 클래스에 존재하는 메소드를 하위 클래스에서 필요에 맞게 재정의하는 것

**오버로딩**

상위 클래스의 메소드와 이름, return 값은 동일하지만, 매개변수만 다른 메소드를 만드는 것.

다양한 상황에서 메소드가 호출될 수 있도록 한다.

### 캡슐화: 정보 은닉

public: 어디든지 접근 가능

protected: 상속/ 같은 패키지 내의 클래스에서 접근 가능

default: 같은 패키지 내의 클래스에서 접근 가능

private: 본인만 접근 가능

**Call By Value**

저장하고 있는 값을 그 값 자체로 판단

**Call By Reference**

저장하고 있는 값을 그 주소로 판단