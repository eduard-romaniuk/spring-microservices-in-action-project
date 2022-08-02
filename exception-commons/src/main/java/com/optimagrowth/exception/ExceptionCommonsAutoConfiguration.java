package com.optimagrowth.exception;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(WebExceptionHandler.class)
public class ExceptionCommonsAutoConfiguration {
    @Bean
    public FeignClientErrorDecoder feignClientErrorDecoder() {
        return new FeignClientErrorDecoder();
    }
}
