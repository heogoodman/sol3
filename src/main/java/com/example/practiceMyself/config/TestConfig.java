package com.example.practiceMyself.config;

import com.example.practiceMyself.dto.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfig {
    @Bean
    public Customer customer() {
        return Customer.builder()
                .name("개발모드")
                .age(10)
                .build();
    }
}
