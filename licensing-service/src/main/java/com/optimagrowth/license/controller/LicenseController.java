package com.optimagrowth.license.controller;

import com.optimagrowth.license.dto.FullLicenseInfoDto;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeoutException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/organization/{organizationId}/license")
public class LicenseController {
    private final LicenseService licenseService;

    @GetMapping("/{licenseId}")
    public License getLicense(@PathVariable("organizationId") String organizationId,
                              @PathVariable("licenseId") String licenseId) {
        return licenseService.getLicense(licenseId, organizationId);
    }

    @GetMapping
    public List<License> findLicenses(@PathVariable("organizationId") String organizationId) throws TimeoutException {
        return licenseService.findLicenses(organizationId);
    }

    @PutMapping
    public License updateLicense(@PathVariable("organizationId") String organizationId,
                                 @RequestBody License request) {
        return licenseService.updateLicense(request, organizationId);
    }

    @PostMapping
    public License createLicense(@PathVariable("organizationId") String organizationId,
                                 @RequestBody License request) {
        return licenseService.createLicense(request, organizationId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping(value = "/{licenseId}")
    public void deleteLicense(@PathVariable("organizationId") String organizationId,
                                 @PathVariable("licenseId") String licenseId) {
        licenseService.deleteLicense(licenseId, organizationId);
    }

    @GetMapping("/{licenseId}/full")
    public FullLicenseInfoDto getLicenseWithClientType(@PathVariable("organizationId") String organizationId,
                                                       @PathVariable("licenseId") String licenseId) {
        return licenseService.getFullLicense(licenseId, organizationId);
    }
}
