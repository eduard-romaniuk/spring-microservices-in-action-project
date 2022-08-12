package com.optimagrowth.messaging.organization;

import com.optimagrowth.messaging.OGMessage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class OrganizationChangeMessage extends OGMessage {
    private ChangeType changeType;
    private String organizationId;
}
