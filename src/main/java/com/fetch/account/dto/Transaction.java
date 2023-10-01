package com.fetch.account.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

    private String payer;
    private int points;
    private LocalDateTime timeStamp;

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
