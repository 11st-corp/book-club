package com.example.object.movieTheater;

import java.math.BigDecimal;

public class Money {
    public static final Money ZERO = Money.wons(0);

    private final BigDecimal amount;

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    Money(BigDecimal amount) {
        this.amount = amount;
    }

    // Money 객체가 amount 인자가 존재하는데 Money 인스턴스명을 amount라고 하는 것이 옳은 것일까?
    public Money plus(Money amount) {
        return new Money(this.amount.add(amount.amount));
    }

    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(
            BigDecimal.valueOf(percent)
        ));
    }

    // 위에서는 굳이 amount라는 인자 명과 인스턴스 명의 겹침때문에 this.amount를 사용해놓고 여기서는 필요없다고 안쓰는게 통일성이 없어보임
    public boolean isLessThan(Money other) {
        return amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }
}
