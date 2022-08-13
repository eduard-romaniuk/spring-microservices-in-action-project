package com.optimagrowth.license.service.external;

import com.optimagrowth.license.dto.OrganizationDto;
import com.optimagrowth.messaging.organization.OrganizationChangeMessage;

public interface ExternalOrganizationService {
    OrganizationDto getOrganization(String organizationId);

    void processOrganizationChangeMessage(OrganizationChangeMessage message);
}
