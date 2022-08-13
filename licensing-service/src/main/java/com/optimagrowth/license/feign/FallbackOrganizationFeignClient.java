package com.optimagrowth.license.feign;

import com.optimagrowth.license.dto.OrganizationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FallbackOrganizationFeignClient implements OrganizationFeignClient {
    @Override
    public OrganizationDto getOrganization(String organizationId) {
        log.debug("Fallback getOrganization");
        return OrganizationDto.builder()
                .organizationId(organizationId)
                .name("Fallback organization")
                .build();
    }
}
