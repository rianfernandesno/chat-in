package com.yui.chat_in.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Value("${API_KEY}")
    public String key;

    @PostConstruct
    public void init(){
        System.out.println(key);
    }
}
