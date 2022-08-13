package com.optimagrowth.license.service.external;

import com.optimagrowth.license.cache.OrganizationCacheRepository;
import com.optimagrowth.license.dto.OrganizationDto;
import com.optimagrowth.license.feign.OrganizationFeignClient;
import com.optimagrowth.messaging.organization.OrganizationChangeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalOrganizationServiceImpl implements ExternalOrganizationService {
    private final OrganizationFeignClient client;
    private final OrganizationCacheRepository cacheRepository;

    @Override
    public OrganizationDto getOrganization(String organizationId) {
        log.debug("Get organization {}", organizationId);
        return findCachedOrganization(organizationId)
                .orElseGet(() -> {
                    log.debug("Organization not found in cache");
                    OrganizationDto organization = client.getOrganization(organizationId);
                    cacheOrganization(organization);
                    return organization;
                });
    }

    @Override
    public void processOrganizationChangeMessage(OrganizationChangeMessage message) {
        log.debug("Process organization change {}", message.getOrganizationId());
        cacheRepository.deleteById(message.getOrganizationId());
    }

    private Optional<OrganizationDto> findCachedOrganization(String organizationId) {
        log.debug("Find cached organization {}", organizationId);
        try {
            Optional<OrganizationDto> result = cacheRepository.findById(organizationId);
            log.debug("Result: {}", result);
            return result;
        } catch (Exception e) {
            log.error("Error encountered while trying to retrieve organization {} from cache. Exception {}", organizationId, e);
            return Optional.empty();
        }
    }

    private void cacheOrganization(OrganizationDto organization) {
        log.debug("Save organization to cache {}", organization.getOrganizationId());
        try {
            cacheRepository.save(organization);
        } catch (Exception e) {
            log.error("Unable to cache organization {}. Exception {}", organization.getOrganizationId(), e);
        }
    }
}
