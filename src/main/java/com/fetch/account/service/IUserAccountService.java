package com.fetch.account.service;

import com.fetch.account.dto.Spend;
import com.fetch.account.dto.Transaction;

import java.util.Map;

public interface IUserAccountService {

    void addPayerTransaction(Transaction transaction);

    Map<String, Integer> spendPoints(Spend spend);

    Map<String,Integer> getPayerBalance();
}
