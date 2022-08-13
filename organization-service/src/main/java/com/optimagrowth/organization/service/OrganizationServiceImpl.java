package com.optimagrowth.organization.service;

import com.optimagrowth.messaging.MessageSender;
import com.optimagrowth.messaging.MessageTopic;
import com.optimagrowth.messaging.organization.ChangeType;
import com.optimagrowth.messaging.organization.OrganizationChangeMessage;
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
    private final MessageSender messageSender;

    @Override
    public Organization getOrganization(String organizationId) {
        log.debug("getOrganization(organizationId={})", organizationId);
        return repository.findByOrganizationId(organizationId)
                .orElseThrow(OGError.LICENSE_NOT_FOUND::toException);
    }

    @Override
    public Organization createOrganization(Organization organization) {
        log.debug("createOrganization(license={})", organization);
        if (organization != null) {
            Organization created = repository.save(organization);
            sendOrganizationChangeMessage(ChangeType.CREATED, created.getOrganizationId());
            return created;
        }
        return null;
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        log.debug("updateOrganization(license={})", organization);
        if (organization != null) {
            Organization saved = repository.save(organization);
            sendOrganizationChangeMessage(ChangeType.UPDATED, saved.getOrganizationId());
            return saved;
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteOrganization(String organizationId) {
        log.debug("deleteLicense(organizationId={})", organizationId);
        repository.deleteByOrganizationId(organizationId);
        sendOrganizationChangeMessage(ChangeType.DELETED, organizationId);
    }

    private void sendOrganizationChangeMessage(ChangeType changeType, String organizationId) {
        messageSender.send(
                MessageTopic.ORGANIZATIONS,
                new OrganizationChangeMessage(changeType, organizationId)
        );
    }
}
