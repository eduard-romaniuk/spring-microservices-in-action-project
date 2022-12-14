package com.optimagrowth.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;

@Slf4j
@RequiredArgsConstructor
public class MessageSender {
    private final StreamBridge streamBridge;

    public void send(MessageTopic topic, Object message) {
        streamBridge.send(topic.name().toLowerCase(), message);
    }
}
