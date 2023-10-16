package com.example.practiceMyself.config;

import com.example.practiceMyself.dto.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("prod")
@Configuration
public class ProdConfig {
    @Bean
    public Customer customer() {
        return Customer.builder()
                .name("운영모드")
                .age(10)
                .build();
    }
}
