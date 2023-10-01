package com.fetch.account.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fetch.account.dto.Transaction;
import com.fetch.account.dto.Spend;
import com.fetch.account.service.UserAccountService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction) {
        try{
            validate(transaction);
            transaction.setPayer(transaction.getPayer().trim());
            userAccountService.addPayerTransaction(transaction);
            return ResponseEntity.status(HttpStatus.OK).body("");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(value = "/spend")
    public ResponseEntity<String> spendPoints(@RequestBody Spend spend) throws JsonProcessingException {
        try{
            validate(spend);
            Map<String,Integer> payerInfo = userAccountService.spendPoints(spend);
            if(payerInfo.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User doesn't have enough points.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectMapper().writeValueAsString(payerInfo));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(value = "/balance")
    public ResponseEntity<Map<String,Integer>> getBalance() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK).body(userAccountService.getPayerBalance());
    }

    private void validate(Transaction transaction) throws Exception {
        if(transaction == null){
            throw new Exception("Payload cannot be null");
        }else {
            if (Strings.isBlank(transaction.getPayer())) {
                throw new Exception("Payer cannot be null/empty");
            }else if(transaction.getTimeStamp()==null){
                throw new Exception("Timestamp cannot be null");
            }
        }
    }

    private void validate(Spend spend) throws Exception {
        if(spend.getPoints()<=0){
            throw new Exception("Spend points cannot be negative.");
        }
    }

}
