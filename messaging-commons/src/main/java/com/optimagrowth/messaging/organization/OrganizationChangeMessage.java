package com.optimagrowth.messaging.organization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationChangeMessage {
    private ChangeType changeType;
    private String organizationId;
}
