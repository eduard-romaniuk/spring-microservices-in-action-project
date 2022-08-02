package com.optimagrowth.organization.repository;

import com.optimagrowth.organization.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
    Optional<Organization> findByOrganizationId(String organizationId);

    @Modifying
    void deleteByOrganizationId(String organizationId);
}
