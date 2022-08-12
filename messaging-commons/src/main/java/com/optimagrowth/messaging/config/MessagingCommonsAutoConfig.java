package com.optimagrowth.messaging.config;

import com.optimagrowth.messaging.MessageSender;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingCommonsAutoConfig {
    @Bean
    public MessageSender messageSender(StreamBridge streamBridge) {
        return new MessageSender(streamBridge);
    }
}
