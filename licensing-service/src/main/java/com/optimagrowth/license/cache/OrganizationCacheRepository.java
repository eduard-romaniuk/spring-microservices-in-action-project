package com.optimagrowth.license.cache;

import com.optimagrowth.license.dto.OrganizationDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationCacheRepository extends CrudRepository<OrganizationDto, String> {
}
