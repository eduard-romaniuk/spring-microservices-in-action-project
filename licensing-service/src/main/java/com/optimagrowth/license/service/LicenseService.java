package com.optimagrowth.license.service;

import com.optimagrowth.license.dto.FullLicenseInfoDto;
import com.optimagrowth.license.model.License;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface LicenseService {
    License getLicense(String licenseId, String organizationId);
    License createLicense(License license, String organizationId);
    License updateLicense(License license, String organizationId);
    void deleteLicense(String licenseId, String organizationId);
    List<License> findLicenses(String organizationId) throws TimeoutException;
    FullLicenseInfoDto getFullLicense(String licenseId, String organizationId);
}
