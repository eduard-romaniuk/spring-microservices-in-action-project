package com.optimagrowth.organization.service;

import com.optimagrowth.organization.model.Organization;

public interface OrganizationService {
    Organization getOrganization(String organizationId);
    Organization createOrganization(Organization organization);
    Organization updateOrganization(Organization organization);
    void deleteOrganization(String organizationId);
}
