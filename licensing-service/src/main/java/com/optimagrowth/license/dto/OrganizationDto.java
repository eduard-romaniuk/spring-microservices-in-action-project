package com.optimagrowth.license.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("organization")
public class OrganizationDto {
    @Id
    private String organizationId;
    private String name;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
}
