package com.optimagrowth.license.service;

import com.optimagrowth.license.feign.OrganizationFeignClient;
import com.optimagrowth.license.config.ResilienceConstants;
import com.optimagrowth.license.dto.FullLicenseInfoDto;
import com.optimagrowth.license.dto.OrganizationDto;
import com.optimagrowth.license.exception.OGError;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {
    private final LicenseRepository repository;
    private final OrganizationFeignClient organizationClient;

    @Override
    public License getLicense(String licenseId, String organizationId) {
        log.debug("getLicense(licenseId={}, organizationId={})", licenseId, organizationId);
        return repository.findByLicenseIdAndOrganizationId(licenseId, organizationId)
                .orElseThrow(OGError.LICENSE_NOT_FOUND::toException);
    }

    @Override
    public License createLicense(License license, String organizationId) {
        log.debug("createLicense(license={}, organizationId={})", license, organizationId);
        if (license != null)
            return repository.save(license.withOrganizationId(organizationId));
        return null;
    }

    @Override
    public License updateLicense(License license, String organizationId) {
        log.debug("updateLicense(license={}, organizationId={})", license, organizationId);
        if (license != null)
            return repository.save(license.withOrganizationId(organizationId));
        return null;
    }

    @Override
    @Transactional
    public void deleteLicense(String licenseId, String organizationId) {
        log.debug("deleteLicense(licenseId={}, organizationId={})", licenseId, organizationId);
        repository.deleteByLicenseIdAndOrganizationId(licenseId, organizationId);
    }

    @Override
    @CircuitBreaker(name = ResilienceConstants.LICENSING_SERVICE)
    @Retry(name = ResilienceConstants.LICENSING_SERVICE, fallbackMethod = "findLicensesFallback")
    public List<License> findLicenses(String organizationId) {
        log.debug("findLicenses(organizationId={})", organizationId);
        return repository.findAllByOrganizationId(organizationId);
    }

    private List<License> findLicensesFallback(String organizationId, Throwable throwable) {
        log.error("Find licenses fallback", throwable);
        return List.of();
    }

    @Override
    public FullLicenseInfoDto getFullLicense(String licenseId, String organizationId) {
        return FullLicenseInfoDto.from(getLicense(licenseId, organizationId))
                .withOrganizationInfo(getOrganizationInfo(organizationId));
    }

    private OrganizationDto getOrganizationInfo(String organizationId) {
        return organizationClient.getOrganization(organizationId);
    }
}
