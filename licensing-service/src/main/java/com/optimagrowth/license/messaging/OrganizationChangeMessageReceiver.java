package com.optimagrowth.license.messaging;

import com.optimagrowth.messaging.organization.OrganizationChangeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class OrganizationChangeMessageReceiver implements Consumer<OrganizationChangeMessage> {
    @Override
    public void accept(OrganizationChangeMessage organizationChangeMessage) {
        log.info("Organization changed: {}", organizationChangeMessage);
    }
}
