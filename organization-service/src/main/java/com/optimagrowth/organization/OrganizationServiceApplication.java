package com.optimagrowth.organization;

import com.optimagrowth.organization.config.ExampleProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class OrganizationServiceApplication implements CommandLineRunner {
    private final ExampleProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(OrganizationServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(properties.getProperty());
    }
}
