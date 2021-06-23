### POJO 를 기반으로 한 스프링 삼각형 → DI/IoC, AOP, PSA

## DI/IoC - 의존성 주입

### 스프링을 통한 의존성 주입

### @Autowired

스프링 어노테이션 : 스프링 설정파일에서 등록된 빈을 찾아 자동 주입해준다

type 매칭 → id 매칭

- @Qualifier 와 조합하여 사용

### @Resource

자바 표준 어노테이션

id 매칭 → type 매칭

- name 속성 사용가능

### 사례연구

- 한 개의 빈이 인터페이스를 구현했으나 id 값이 없는 경우 <br>
  → (실행)
- 두 개의 빈이 같은 인터페이스를 구현했지만 id 값이 같은 빈이 없는 경우 <br>
  → (오류) @Autowired, @Resource 같은 오류
- 두 개의 빈이 같은 인터페이스를 구현했지만 하나의 빈에서만 id 값이 일치하는 경우 <br>
  → (실행)
- 두 개의 빈이 같은 인터페이스를 구현했지만 일치하는 id 값이 없는 경우 <br>
  → (오류) No unique bean / @Autowired, @Resource 같은 오류
- 인터페이스를 구현하지 않았지만 id 값이 일치하는 경우 <br>
  → (오류) <br>
  @Resource → Bean named '~' must be of type : 이름을 먼저 검색<br>
  @Autowired → No matching bean of type : 타입을 먼저 검색
- 두 개의 빈이 같은 인터페이스를 구현하고 일치하는 id 값이 없지만 @Resource 어노테이션의 name 속성이 id 값과 일치하는 경우 → 실행

    ```java
    public class Team {
    	@Resource(name="developer")
    	Developer developer;

    	public String getDeveloperPart() {
    		return "part name : " + developer.getPart();
    	}
    }
    ```

    @Autowired 를 사용하는 경우 위와 같게 하려면

    ```java
    public class Team {
    	@Autowired
    	@Qualifier("developer")
    	Developer developer;

    	public String getDeveloperPart() {
    		return "part name : " + developer.getPart();
    	}
    }
    ```

---

## AOP - 관점 지향 프로그래밍

다수의 모듈에서 공통적으로 나타나는 로직(횡단 관심사: Cross-Cutting Concern)을 주입하는 프로그래밍 모델.

횡단 관심사는 신경쓰지 않고 핵심 관심사에 집중하여 로직을 작성할 수 있는 장점이 있음

인터페이스, 프록시, 런타임 기반 

### 로직을 주입할 수 있는 위치

- Around - 메서드 전 구역
- Before - 매서드 시작 전
- After - 메서드 종료 후
- AfterReturning - 메서드 정상 종료 후
- AfterThrowing - 메서드에서 예외가 발생하면서 종료된 후

### 적용 방법은

- CGLib 라이브러리를 이용하는 방법

    코드를 변경할 수 없는 서드 파티 모듈 안에 final 로 선언된 클래스에 AOP 를 적용해야 하는 경우에 사용

- 인터페이스를 사용하는 방법

### 어노테이션

@Aspect : 이 클래스를 AOP 에 사용하겠다는 표시

@Before : 대상 메서드 실행 전에 이 메서드를 실행하겠다는 표시

### 스프링 AOP의 프록시

스프링 AOP 는 프록시 기반으로, <br>
타겟 메서드가 주고 받는 내용을 감시하거나 조작이 가능하며, <br>
프록시의 존재는 호출 객체, 호출되는 메서드에서 알 수 없으며<br>
스프링 프레임 워크만 프록시의 존재를 알고 있다

---

## PSA - 일관성 있는 서비스 추상화

다수의 공통 기술을 같은 방식(인터페이스를 활용)으로 제어할 수 있도록 한 서비스 추상화

스프링은 OXM, ORM, 캐시, 트랜잭션 등 다양한 기술에 대한 PSA 를 제공
