package com.familyfinances;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
public class FamilyFinancesApplication {
    public static void main(String[] args) {
        SpringApplication.run(FamilyFinancesApplication.class, args);
    }
}
