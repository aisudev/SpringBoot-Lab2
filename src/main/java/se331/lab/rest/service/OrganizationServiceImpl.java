package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.EventDao;
import se331.lab.rest.dao.OrganizationDao;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organization;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    @Autowired
    OrganizationDao orgDao;
    @Override
    public Integer getOrganizationSize() {
        return orgDao.getOrganizationSize();
    }

    @Override
    public Page<Organization> getOrganizations(Integer pageSize, Integer page) {
        return orgDao.getOrganizations(pageSize, page);
    }

    @Override
    public Organization getOrganization(Long id) {
        return orgDao.getOrganization(id);
    }

    @Override
    public Organization save(Organization org) {
        return orgDao.save(org);
    }
}
