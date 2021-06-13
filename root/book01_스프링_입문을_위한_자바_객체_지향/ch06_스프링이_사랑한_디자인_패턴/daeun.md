# ch06. 스프링이 사랑한 디자인 패턴
디자인 패턴은 객체 지향의 특성 중 상속, 인터페이스, 합성(객체를 속성으로 사용)을 이용한다.

## 어댑터 패턴

**호출 당하는 쪽의 메서드를 호출하는 쪽의 코드에 대응하도록 중간에 변환기를 통해 호출하는 패턴**

- 개방 폐쇄 원칙을 활용한 설계 패턴
- 서로 다른 두 인터페이스 사이에 통신이 가능하도록 변환기의 역할을 수행
- JDBC와 JRE는 어댑터의 역할을 수행

![](https://i.imgur.com/7mb5wmM.png)


```java
public interface Dog {
	
	public void bark();
	public void eat();
}
```

```java
public class Poodle implements Dog {
	@Override
	public void bark() {
		System.out.println("멍멍");
	}

	@Override
	public void eat() {
		System.out.println("쩝쩝");
	}
}
```

```java
public interface Cat {
	public void cry();
	public void eat();
}
```

```java
public class Persian implements Cat {
	@Override
	public void cry() {
		System.out.println("야옹");
	}

	@Override
	public void eat() {
		System.out.println("냠냠");
	}
}
```

```java
public class PersianAdapter implements Dog {

	Persian persian;

  public PersianAdapter(Persian persian) {
	  this.persian = persian;
  }

  @Override
  public void bark(){ 
     persian.cry();
  }

  @Override
  public void eat() {
     persian.eat();
  }
}
```

```java
public class DogTestDrive {
	public static void main(String[] args) {
		Poodle poodle = new Poodle();
		
		Persian persian = new Persian();
		Dog persianAdapter = new PersianAdapter(persian);
		
		System.out.println("The persian says...");
		persian.cry();
		persian.eat();

		System.out.println("The poodle says...");
		testDog(dog);

		System.out.println("The persianAdapter says...");
		testDog(persianAdapter);
	}

	public static void testDog(Dog dog){
		dog.bark();
		dog.eat();
	}
}
```

클라이언트 → request() → 어댑터 - traslatedRequest() → 어댑티

1. 클라이언트에서 타겟 인터페이스를 사용하여 메소드를 호출하며 어댑터에 요청한다.
2. 어댑터에서는 어댑티 인터페이스를 사용하여 그 요청을 어댑티에 대한 하나 이상의 메소드를 호출로 변환한다.
3. 클라이언트는 호출 결과를 받긴 하지만 중간에 어댑터가 껴있는지는 알지 못한다.

## 프록시 패턴

**실제 기능을 수행하는 객체 대신 가상의 객체(proxy object)를 사용해 로직의 흐름을 제어하는 디자인 패턴**

- 개방 폐쇄 원칙과 의존 역전 원칙이 적용된 설계 패턴

```java
public class Service {
	 public String runSomething() {
		return "서비스 짱!!!";
	}
}
```

```java
public class ClientWithNoProxy {
	public static void main(String[] args) {
		// 프록시를 이용하지 않은 호출
		Service service = new Service();
		System.out.println(service.runSomething());
	}
}
```

프록시 패턴의 경우 실제 서비스 객체가 가진 메서드와 같은 이름의 메서드를 사용하는데, 이를 위해 인터페이스를 사용한다. 

인터페이스를 사용하면 서비스 객체가 들어갈 자리에 **대리자 객체**를 대신 투입

클라이언트 쪽에서는 실제 서비스 객체를 통해 메서드를 호출하고 반환값을 받는지, 대리자 객체를 통해 메서드를 호출하고 반환값을 받는지 전혀 모름.

```java
public interface IService {
	String runSomething();
}
```

```java
public class Service implements IService {
	public String runSomething(){
		return "서비스 짱!!!";
	}
}
```

```java
public class Proxy implements IService {
	IService service1;

	public String runSomething() {
		System.out.println("호출에 대한 흐름 제어가 주목적, 반환 결과를 그대로 전달");
		service1 = new Service();
		return service1.runSomething();
	}
}
```

```java
public class ClientWithProxy {
	public static void main(String[] args) {
		// 프록시를 이용한 호출
		IService proxy = new Proxy();
		System.out.println(proxy.runSomething());
	}
}
```

- 원래 하려던 기능을 수행하며 그외의 부가적인 작업(로깅, 인증, 네트워크 통신)을 수행하기에 좋다.
- 비용이 많이 드는 연산(DB 쿼리, 대용량 텍스트 파일 등)을 실제로 필요한 시점에 수행할 수 있다.

유저가 죽고 나서 다시 살아 날 때 로딩시간이 걸립니다. 데이터가 큰 이미지나 그래픽을 로딩하는데 시간이 걸리기 때문이죠 이럴 경우 유저는 짜증날 겁니다. 계속 아무것도 안하고 기다려야 하니까 이럴때 프록시는 제어 흐름을 통해 큰 데이터가 로딩 될 때 까지 현재까지 완료된 것을 우선적으로 보여줍니다. 즉 배틀그라운드에서 비행기 타기 전 사람들이 어느 한정된 공간에서 캐릭터를 움직일 수 있게라도 해주는 것이죠... (https://limkydev.tistory.com/79)

- 사용자 입장에서는 프록시 객체나 실제 객체나 사용법은 유사하므로 사용성이 좋다.
- **중요한 것은 흐름제어만 할 뿐 결과 값을 조작하거나 변경시키면 안된다.**

## 데코레이터 패턴

**기능을 확장시키는 경우가 많을 때, 각 추가 기능을 Decorator 클래스로 정의한 후 필요한 Decorator 객체를 조합함으로써 추가 기능의 조합을 설계**

- 추가 기능의 수가 많을수록 효과가 큼
- 개방폐쇄 원칙과 의존 역전 원칙이 적용된 설계 패턴

```java
public interface Display {
	void draw();
}
```

```java
public class RoadDisplay implements Display {
	@Override
	public void draw() {
		System.out.println("기본 도로 표시");
	}
}
```

```java
public abstract class DisplayDecorator implements Display {
	private Display decoratedDisplay;
	
	public DisplayDecorator(Display decoratedDisplay) {
		this.decoratedDisplay = decoratedDisplay;
	}
	
	@Override
	public void draw() {
		decoratedDisplay.draw();
	}
}
```

```java
public class LaneDecorator extends DisplayDecorator {
	public LaneDecorator(Display decoratedDisplay) {
		super(decoratedDisplay);
	}
	@Override
	public void draw() {
		super.draw(); // 설정된 기존 표시 기능을 수행
		drawLane(); // 추가적으로 차선을 표시
	}
	private void drawLane() {
		System.out.println("\t차선 표시");
	}
}
```

```java
public class Client {
  public static void main(String[] args) {
      Display road = new RoadDisplay();
      road.draw(); // 기본 도로 표시
      Display roadWithLane = new LaneDecorator(new RoadDisplay());
      roadWithLane.draw(); // 기본 도로 표시 + 차선 표시
      Display roadWithTraffic = new TrafficDecorator(new RoadDisplay());
      roadWithTraffic.draw(); // 기본 도로 표시 + 교통량 표시
  }
}
```

## 싱글턴 패턴

**인스턴스 하나만 만들어 사용하기 위한 패턴**

- new 를 실행할 수 없도록 생성자에 private 접근 제어자를 지정
- 유일한 단일 객체를 반환할 수 있는 정적 메서드 필요
- 유일한 단일 객체를 참조할 정적 참조 변수 필요
- 단일 객체는 쓰기 가능한 속성을 갖지 않는 것이 정석
- 단일 객체 참조 변수가 참조하는 단일 객체를 반환하는 getInstance() 정적 메서드를 갖음

## 템플릿 메서드 패턴

**전반적인 코드는 동일하나, 일부분만 캡슐화해 하위 클래스가 오버라이딩하도록 유도하는 패턴**

- 전체적으로 동일한 흐름이지만, 부분적으로 다른 경우 메서드의 코드 중복을 최소화할 때 유용

```java
public class Dog {
	public void playWithOwner() {
		System.out.println("귀염둥이 이리온");
		System.out.println("멍멍!!");
		System.out.println("꼬리 살랑살랑~");
		System.out.println("잘했어");
	}
}
```

```java
public class Cat {
	public void playWithOwner() {
		System.out.println("귀염둥이 이리온");
		System.out.println("야옹야옹!!");
		System.out.println("꼬리 살랑살랑~");
		System.out.println("잘했어");
	}
}
```

이 경우 전체적으로 비슷하나 의성어만 다른 것을 볼 수 있다.

```java
public abstract class Animal {
	//템플릿 메서드
	public void playWithOwner() {
		System.out.println("귀염둥이 이리온...");
		play();
		runSomething();
		System.out.println("잘했어");
	}

  //추상 메서드
  abstract void play();

	//Hook 메서드
	void runSomething() {
		System.out.println("꼬리 살랑 살랑~");
	}
}
```

```java
public class Dog extends Animal {
	@Override
	void play() {
		System.out.println("멍멍!!");
	}

	@Override
	void runSomething() {
		System.out.println("멍!! 꼬리 살랑 살랑~")
	}
}
```

```java
public class Cat extends Animal {
	@Override
	void play() {
		System.out.println("야옹야옹!!");
	}

	@Override
	void runSomething() {
		System.out.println("야옹야옹!! 꼬리 살랑 살랑~")
	}
}
```

템플릿 메서드 : 공통 로직을 수행, 로직 중에 하위 클래스에서 오버라이딩한 추상 메서드/훅 메서드를 호출

- 템플릿 메서드 패턴은 의존 역전 원칙을 활용하고 있음

## 팩터리 메서드 패턴

**객체 생성 처리를 서브 클래스로 분리해 처리하도록 캡슐화 하는 패턴**

- 팩터리 메서드 패턴은 의존 역전 원칙을 활용

```java
public abstract class Animal {
	//추상 팩터리 메서드
	abstract AnimalToy getToy();
}
```

```java
// 팩터리 메서드가 생성할 객체의 상위 클래스
public abstract class AnimalToy {
	abstract void identify();
}
```

```java
public class Dog extends Animal {
	// 추상 팩터리 메서드 오버라이딩
	@Override
	AnimalToy getToy(){
		return new DogToy();
	}
}
```

```java
public class DogToy extends AnimalToy {
	public void identify() {
		System.out.println("나는 테니스 공! 강아지의 친구!");
	}
}
```

```java
public class Driver {
	public static void main(String[] args) {
	// 팩터리 메서드를 보유한 객체 생성
	Animal bolt = new Dog();

	// 팩터리 메서드가 반환하는 객체
	AnimalToy boltBall = bolt.getToy();

	// 팩터리 메서드가 반환한 객체들을 사용
	boltBall.identify();
	}
}
```

패턴이 너무 많다...................

## 전략 패턴

**클래스로 캡슐화해 동적으로 행위를 쉽게 바꿀 수 있게 해주는 패턴**

→ 클라이언트가 전략을 생성해 전략을 실행할 컨텍스트에 주입하는 패턴

- 개방 폐쇄 원칙과 의존 역전 원칙이 적용된 것

```java
public interface Strategy {
	public abstract void runSrategy();
}
```

```java
public class StrategyGun implements Strategy {
	@Override
	public void runStrategy() {
		System.out.println("탕, 타당, 타다당");
	}
}
```

```java
public class StrategySword implements Strategy {
	@Override
	public void runStrategy() {
		System.out.println("챙..채채챙 챙챙");
	}
}
```

```java
public class StrategyBow implements Strategy {
	@Override
	public void runStrategy() {
		System.out.println("슝..쐐액..쉑, 최종 병기");
	}
}
```

```java
public class Soldirer {
	void runContext(Strategy strategy) {
		System.out.println("전투 시작");
		strategy.runStrategy();
		System.out.println("전투 종료");
	}
}
```

```java
public class Client {
	public static void main(String[] args) {
		Strategy strategy = null;
		Soldier rambo = new Soldier();

		//총을 람보에게 전달해서 전투를 수행하게 한다.
		strategy = new StrategyGun();
		rambo.runContext(strategy);

		//검을 람보에게 전달해서 전투를 수행하게 한다.
		strategy = new StrategySword();
		rambo.runContext(strategy);

		//활을 람보에게 전달해서 전투를 수행하게 한다.
		strategy = new StrategyBow();
		rambo.runContext(strategy);
	}
}
```

## 템플릿 콜백 패턴

전략 패턴과 동일하지만 전략을 익명 내부 클래스로 정의해서 사용한다는 특징을 가짐.

- DI에서 사용하는 특별한 형태의 전략 패턴
- 전략 패턴의 변형

```java
public class Client {
	public static void main(String[] args) {
		Strategy strategy = null;
		Soldier rambo = new Soldier();

		rambo.runContext(new Strategy() {
			@Override
			public void runStrategy() {
				System.out.println("총! 총초초초총 총! 총!");
			}
		});

		rambo.runContext(new Strategy() {
			@Override
			public void runStrategy() {
				System.out.println("칼! 카라칼카라 칼! 칼!");
			}
		});
	}
}
```

## 스프링이 사랑한 다른 패턴들

- 프론트 컨트롤러 패턴
- MVC패턴

참조: [https://gmlwjd9405.github.io](https://gmlwjd9405.github.io/2018/07/06/strategy-pattern.html)