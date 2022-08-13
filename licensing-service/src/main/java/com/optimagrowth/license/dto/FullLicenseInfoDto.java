package com.optimagrowth.license.dto;

import com.optimagrowth.license.model.License;
import lombok.Data;

@Data
public class FullLicenseInfoDto {
    private String licenseId;
    private String description;
    private String productName;
    private String licenseType;
    private String organizationId;
    private String organizationName;
    private String contactName;
    private String contactEmail;
    private String contactPhone;

    public static FullLicenseInfoDto from(License license) {
        FullLicenseInfoDto result = new FullLicenseInfoDto();
        result.setLicenseId(license.getLicenseId());
        result.setDescription(license.getDescription());
        result.setProductName(license.getProductName());
        result.setLicenseType(license.getLicenseType());
        result.setOrganizationId(license.getOrganizationId());
        return result;
    }

    public FullLicenseInfoDto withOrganizationInfo(OrganizationDto organizationInfo) {
        organizationName = organizationInfo.getName();
        contactName = organizationInfo.getContactName();
        contactEmail = organizationInfo.getContactEmail();
        contactPhone = organizationInfo.getContactPhone();
        return this;
    }
}
