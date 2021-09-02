package se331.lab.rest.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organization;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("manual")
public class OrganizationDaoImpl implements OrganizationDao {
    List<Organization> orgList;

    @PostConstruct
    public void init() {
        orgList = new ArrayList<Organization>();

    }

    @Override
    public Integer getOrganizationSize() {
        return orgList.size();
    }

    @Override
    public Page<Organization> getOrganizations(Integer pageSize, Integer page) {
        pageSize = pageSize == null ? orgList.size() : pageSize;
        page = page == null ? 1 : page;
        int firstIndex = (page - 1) * pageSize;
        return new PageImpl<Organization>(orgList.subList(firstIndex,firstIndex+pageSize), PageRequest.of(page, pageSize), orgList.size());
    }

    @Override
    public Organization getOrganization(Long id) {
        return orgList.stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Organization save(Organization org) {
        org.setId(orgList.get(orgList.size()-1).getId()+1);
        orgList.add(org);
        return org;
    }
}
