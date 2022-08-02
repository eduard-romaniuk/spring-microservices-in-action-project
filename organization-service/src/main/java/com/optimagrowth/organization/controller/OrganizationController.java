package com.optimagrowth.organization.controller;

import com.optimagrowth.organization.exception.OGException;
import com.optimagrowth.organization.model.Organization;
import com.optimagrowth.organization.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    @GetMapping("/{organizationId}")
    public Organization getLicense(@PathVariable("organizationId") String organizationId) {
        return organizationService.getOrganization(organizationId);
    }

    @PutMapping
    public Organization updateLicense(@RequestBody Organization request) {
        return organizationService.updateOrganization(request);
    }

    @PostMapping
    public Organization createLicense(@RequestBody Organization request) {
        return organizationService.createOrganization(request);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value = "/{organizationId}")
    public void deleteLicense(@PathVariable("organizationId") String organizationId) {
        organizationService.deleteOrganization(organizationId);
    }

    @ExceptionHandler(OGException.class)
    public ResponseEntity<OGException.Dto> ogException(OGException exception) {
        return new ResponseEntity<>(exception.toDto(), exception.getOgError().getHttpStatus());
    }
}
