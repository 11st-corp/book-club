package com.example.object.movieTheater;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
