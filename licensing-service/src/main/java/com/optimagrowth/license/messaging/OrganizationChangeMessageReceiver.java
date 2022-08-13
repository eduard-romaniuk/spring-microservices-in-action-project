package com.optimagrowth.license.messaging;

import com.optimagrowth.license.service.external.ExternalOrganizationService;
import com.optimagrowth.messaging.organization.OrganizationChangeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrganizationChangeMessageReceiver implements Consumer<OrganizationChangeMessage> {
    private final ExternalOrganizationService externalOrganizationService;

    @Override
    public void accept(OrganizationChangeMessage message) {
        log.debug("Organization changed: {}", message);
        externalOrganizationService.processOrganizationChangeMessage(message);
    }
}
