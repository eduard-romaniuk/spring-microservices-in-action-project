package com.optimagrowth.organization.service;

import com.optimagrowth.organization.exception.OGError;
import com.optimagrowth.organization.model.Organization;
import com.optimagrowth.organization.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository repository;

    @Override
    public Organization getOrganization(String organizationId) {
        log.debug("getOrganization(organizationId={})", organizationId);
        return repository.findByOrganizationId(organizationId)
                .orElseThrow(OGError.LICENSE_NOT_FOUND::toException);
    }

    @Override
    public Organization createOrganization(Organization organization) {
        log.debug("createOrganization(license={})", organization);
        if (organization != null)
            return repository.save(organization);
        return null;
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        log.debug("updateOrganization(license={})", organization);
        if (organization != null)
            return repository.save(organization);
        return null;
    }

    @Override
    @Transactional
    public void deleteOrganization(String organizationId) {
        log.debug("deleteLicense(organizationId={})", organizationId);
        repository.deleteByOrganizationId(organizationId);
    }
}
