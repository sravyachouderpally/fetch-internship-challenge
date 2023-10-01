package com.fetch.account.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class UserAccount implements Serializable {

    private PriorityQueue<Transaction> transactions;
    private HashMap<String, Integer> payerToPoints;
    int balance;

    public UserAccount(){
        Comparator<Transaction> timestampComparator = new Comparator<Transaction>() {
            @Override
            public int compare(Transaction payerDetails1, Transaction payerDetails2) { //payerInfo
                LocalDateTime localDateTime1 = LocalDateTime.of(payerDetails1.getTimeStamp().getYear(), payerDetails1.getTimeStamp().getMonth(),  payerDetails1.getTimeStamp().getDayOfMonth(),  payerDetails1.getTimeStamp().getHour(),  payerDetails1.getTimeStamp().getMinute(),  payerDetails1.getTimeStamp().getSecond());
                LocalDateTime localDateTime2 = LocalDateTime.of(payerDetails2.getTimeStamp().getYear(), payerDetails2.getTimeStamp().getMonth(),  payerDetails2.getTimeStamp().getDayOfMonth(),  payerDetails2.getTimeStamp().getHour(),  payerDetails2.getTimeStamp().getMinute(),  payerDetails2.getTimeStamp().getSecond());
                return localDateTime1.compareTo(localDateTime2);
            }
        };
        this.transactions = new PriorityQueue<>(timestampComparator);
        this.payerToPoints = new HashMap<>();
        balance = 0;
    }


    public PriorityQueue<Transaction> getTransactions() {
        return transactions;
    }

    public HashMap<String, Integer> getPayerToPoints() {
        return payerToPoints;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
