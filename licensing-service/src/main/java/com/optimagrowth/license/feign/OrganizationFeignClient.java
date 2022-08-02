package com.optimagrowth.license.feign;

import com.optimagrowth.license.dto.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "organization-service",
        fallback = FallbackOrganizationFeignClient.class
)
public interface OrganizationFeignClient {
    @GetMapping("/organization/{organizationId}")
    OrganizationDto getOrganization(@PathVariable String organizationId);
}
