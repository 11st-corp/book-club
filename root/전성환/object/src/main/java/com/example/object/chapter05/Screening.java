package com.example.object.chapter05;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;
    
    public Reservation reserve(Customer customer, int audienceCount) {
        return new Rewervation(customer, this, calculateFee(audienceCount), audienceCount);
    }
    
    private Money calculateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }

    public int getSequence() {
        return sequence;
    }
}
