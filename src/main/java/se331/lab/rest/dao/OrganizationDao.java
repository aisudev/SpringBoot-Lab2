package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import se331.lab.rest.entity.Organization;

public interface OrganizationDao {
    Integer getOrganizationSize();
    Page<Organization> getOrganizations(Integer pageSize, Integer page);
    Organization getOrganization(Long id);
    Organization save(Organization event);
}
