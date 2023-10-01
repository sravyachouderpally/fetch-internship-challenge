package com.fetch.account.config;

import com.fetch.account.dto.UserAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserAccount databaseObject(){
        return new UserAccount();
    }
}
