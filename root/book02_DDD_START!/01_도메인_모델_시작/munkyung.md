### 도메인

소프트웨어로 해결하고자하는 문제 영역

### 도메인 모델

특정 도메인을 개념적으로 표현한 것

여러 관계자들이 동일한 모습으로 도메인 지식을 공유하고 이해는데 도움이 된다

도메인 모델은 도메인 자체를 이해하기 위한 개념으로 구현 기술에 맞는 구현 모델이 필요하며 표현방식은 다양하다

### 도메인 모델 패턴

도메인 규칙을 객체 지향 기법으로 구현하는 패턴이 도메인 모델 패턴

- 사용자 인터페이스 & 표현 : 사용자가 요청가능한 정보를 보여줌
- 응용 : 사용자 요청기능을 도메인 계층을 조합해서 실행. 로직을 직접 구현하지 않음
- 도메인 : 시스템이 제공할 도메인 규칙 구현
    - 핵심 규칙을 구현한 코드는 도메인 모델에만 위치하기 때문에 규칙이 바뀌거나 규칙을 확장해야 할 때 다른 코드에 영향을 덜 주면서 수정사항을 반영할 수 있다.
- Infrastructure : DB, 외부 시스템과의 연동 등 처리

### 엔티티와 밸류

도메인을 올바르게 설계하고 구현하기 위한 엔티티와 밸류의 차이

- **엔티티** : 엔티티는 각 고유의 식별자를 가진다.
    - 식별자는 특정규칙, UUID, 일련번호(시퀸스나 DB의 자동증가), 직접입력을 통해 생성한다
    - 두 엔티티가 같은지 확인하려면 식별자를 이용해 확인한다
    - 식별자는 단순한 문자열이 아니라 도메인에서 특별한 의미를 지니는 경우가 많기 때문에 식별자를 위한 **밸류 타입**을 사용해서 의미가 잘 드러나도록 할 수 있다.
- **밸류** : 개념적으로 완전한 하나를 표현

    ```java
    // 쇼핑 정보
    public class ShippingInfo {
    		// 받는 사람
    		private String receiverName;
    		private String receiverPhoneNumber;
    		// 주소
    ****		private String shippingAddress1;
    		private String shippingAddress2;
    		private String shippingZipcode;
    }
    ```

    ```java
    // 받는 사람
    public class Receiver {
    		private String name;
    		private String phoneNumber;
    }

    // 주소
    public class Address {
    ****		private String address1;
    		private String address2;
    		private String zipcode;
    }
    ```

    ```java
    // 쇼핑 정보
    public class ShippingInfo {
    		// 받는 사람
    		private Receiver receiver;
    		// 주소
    ****		private Address address;
    }
    ```

    - 의미를 명확하게 표현하기 위해 밸류타입을 사용
    - 밸류타입을 위한 기능을 추가할 수 있다는 장점
    - 불변타입의 장점은 안전한 코드를 작성할 수 있다는 점. 참조투명성과 스레드에 안전한 특징을 가짐.
    - 두 밸류 객체가 같은지 확인하려면 모든 속성을 비교해야 한다.

### 도메인 용어

도메인에서 사용하는 용어를 최대한 코드에 반영하면 도메인 용어를 코드로 해석하는 과정이 줄어든다. 코드의 가독성을 높여 코드를 분석하고 이해하는 시간을 절약할 수 있다. 도메인 용어에 알맞은 단어를 찾는 시간을 아까워말자~
