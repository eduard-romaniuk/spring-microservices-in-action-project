package com.optimagrowth.license.dto;

import lombok.Builder;

@Builder
public record OrganizationDto(
        int id,
        String organizationId,
        String name,
        String contactName,
        String contactEmail,
        String contactPhone
) {}
