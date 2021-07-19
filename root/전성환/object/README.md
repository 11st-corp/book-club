# 1. 객체, 설계

## theater package

- Invitation - 당첨자에게 발송되는 초대장
  - 공연을 관람할 수 있는 초대일자(when)
- Ticket - 공연을 관람하기 위한 티켓
- Bag - 관광객이 소지품을 보관할 가방
  - Invitation
  - Ticket
  - Amount
  - 이벤트에 당첨된 관람객의 가방 안에는 현금과 초대장이 들어있지만 이벤트에 당첨되지 않은 관람객의 가방 안에는 초대장이 들어있지 않을 것이다. -> Bag의 인스턴스를 생성하는 시점에 이 제약을 강제할 수 있도록 생성자를 추가
- Audience - 관람객이라는 개념을 구현. 소지품을 보관하기 위한 가방을 소지
  - Bag
- TicketOffice - 초대장을 티켓으로 교환하거나 구매할 장소
- TicketSeller - 매표소에서 일할 판매원



# 2. 객체지향 프로그래밍

## movieTheater package

- Movie - 영화
  - title - 제목
  - runningTime - 상영시간
  - fee - 기본 요금
  - discountPolicy - 할인 정책
- Screening - 상영
  - sequence - 순번
  - whenScreend - 상영 시작 시간
- DiscountPolicy - 할인 정책
  - AmountDiscountPolicy - 금액 할인 정책
  - PercentDiscountPolicy - 비율 할인 정책
- DiscountCondition - 할인 조건
  - SequenceCondition - 순번 조건
  - PeriodCondition - 기간 조건
- Reservation - 예매



# 4. 설계 품질과 트레이드오프

- Movie
  - title
  - runningTime
  - fee
  - discountConditions
  - movieType - 영화의 할인 정책 타입
  - discountAmount - 할인액
  - discountPercent - 할인비율
- Screening
  - movie
  - sequence
  - whenScreened
- DiscountCondition
  - DiscountConditionType
  - sequence
  - dayOfWeek
  - startTime
  - endTime
- Reservation
  - customer
  - screening
  - fee
  - audienceCount
- Customer
  - name
  - id

