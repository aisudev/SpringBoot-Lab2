package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Organization;
import se331.lab.rest.repository.OrganizationRepository;

@Repository
@Profile("db")
public class OrganizationDaoDbImpl implements OrganizationDao{
    @Autowired
    OrganizationRepository orgRepository;
    @Override
    public Integer getOrganizationSize() {
        return Math.toIntExact(orgRepository.count());
    }

    @Override
    public Page<Organization> getOrganizations(Integer pageSize, Integer page) {
        return orgRepository.findAll(PageRequest.of(page-1, pageSize));
    }

    @Override
    public Organization getOrganization(Long id) {
        return orgRepository.findById(id).orElse(null);
    }

    @Override
    public Organization save(Organization org) {
        return orgRepository.save(org);
    }
}
