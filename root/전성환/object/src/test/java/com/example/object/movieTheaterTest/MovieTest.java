package com.example.object.movieTheaterTest;

import com.example.object.movieTheater.AmountDiscountPolicy;
import com.example.object.movieTheater.Money;
import com.example.object.movieTheater.Movie;
import com.example.object.movieTheater.PercentDiscountPolicy;
import com.example.object.movieTheater.PeriodCondition;
import com.example.object.movieTheater.SequenceCondition;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

public class MovieTest {

    AmountDiscountPolicy sampleAmountDiscountPolicy = new AmountDiscountPolicy(Money.wons(800),
                                                                               new SequenceCondition(1),
                                                                               new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0),
                                                                                                   LocalTime.of(11, 59)),
                                                                               new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(20, 59)));

    PercentDiscountPolicy samplePercentDiscountPolicy = new PercentDiscountPolicy(0.1,
                                                                                  new SequenceCondition(1),
                                                                                  new PeriodCondition(DayOfWeek.MONDAY, LocalTime.of(10, 0),
                                                                                                      LocalTime.of(11, 59)),
                                                                                  new PeriodCondition(DayOfWeek.THURSDAY, LocalTime.of(10, 0), LocalTime.of(20, 59)));

    @Test
    public void CreateMovieTest() {
        Movie avatar = new Movie("아바타",
                                 Duration.ofMinutes(120),
                                 Money.wons(10000),
                                 sampleAmountDiscountPolicy);

    }
}
