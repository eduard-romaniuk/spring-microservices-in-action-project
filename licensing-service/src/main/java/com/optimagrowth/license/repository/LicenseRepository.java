package com.optimagrowth.license.repository;

import com.optimagrowth.license.model.License;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LicenseRepository extends JpaRepository<License, Integer> {
    Optional<License> findByLicenseIdAndOrganizationId(String licenseId, String organizationId);

    void deleteByLicenseIdAndOrganizationId(String licenseId, String organizationId);

    List<License> findAllByOrganizationId(String organizationId);
}
