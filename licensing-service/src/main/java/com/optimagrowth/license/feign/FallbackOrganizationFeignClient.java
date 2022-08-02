package com.optimagrowth.license.feign;

import com.optimagrowth.license.dto.OrganizationDto;
import org.springframework.stereotype.Component;

@Component
public class FallbackOrganizationFeignClient implements OrganizationFeignClient {
    @Override
    public OrganizationDto getOrganization(String organizationId) {
        return OrganizationDto.builder()
                .organizationId(organizationId)
                .name("Fallback organization")
                .build();
    }
}
