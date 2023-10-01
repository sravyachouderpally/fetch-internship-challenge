package com.fetch.account.service;

import com.fetch.account.dto.UserAccount;
import com.fetch.account.dto.Spend;
import com.fetch.account.dto.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserAccountService implements IUserAccountService{
    private final UserAccount userAccount;
    @Autowired
    public UserAccountService(UserAccount userAccount){
        this.userAccount = userAccount;
    }
    @Override
    public void addPayerTransaction(Transaction transaction) {
        userAccount.getTransactions().add(transaction);
        HashMap<String,Integer> payerToPoints = userAccount.getPayerToPoints();
        if(!payerToPoints.containsKey(transaction.getPayer())){
            payerToPoints.put(transaction.getPayer(), transaction.getPoints());
        }else{
            payerToPoints.put(transaction.getPayer(), payerToPoints.get(transaction.getPayer())+ transaction.getPoints());
        }
        userAccount.setBalance(userAccount.getBalance()+transaction.getPoints());
    }

    public Map<String, Integer> spendPoints(Spend spend) {
        if (spend.getPoints() > userAccount.getBalance()) {
            return Collections.emptyMap();
        }
        int points = spend.getPoints();
        Map<String, Integer> resultMap = new HashMap<>();
        while (!userAccount.getTransactions().isEmpty() && points > 0) {
            Transaction transaction = userAccount.getTransactions().poll();
            int pointsDeducted = 0;
            if(transaction.getPoints() > points) {
                pointsDeducted = points;
                transaction.setPoints(transaction.getPoints() - points);
                userAccount.getTransactions().add(transaction);
            } else{
                pointsDeducted = transaction.getPoints();
            }
            userAccount.setBalance(userAccount.getBalance() - pointsDeducted);
            userAccount.getPayerToPoints().put(transaction.getPayer(), userAccount.getPayerToPoints().get(transaction.getPayer()) - pointsDeducted);
            if (resultMap.containsKey(transaction.getPayer())) {
                resultMap.put(transaction.getPayer(), resultMap.get(transaction.getPayer()) - pointsDeducted);
            } else {
                resultMap.put(transaction.getPayer(), -pointsDeducted);
            }
            points = points - pointsDeducted;
        }
        return resultMap;
    }

    @Override
    public Map<String,Integer> getPayerBalance() {
        return userAccount.getPayerToPoints();
    }


}
