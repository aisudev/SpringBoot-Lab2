package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Organization;
import se331.lab.rest.service.OrganizationService;

@Controller
public class OrganizationController {
    @Autowired
    OrganizationService orgService;

    @GetMapping("org")
    public ResponseEntity<?> getOrganizationLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page) {
        Page<Organization> pageOutput = orgService.getOrganizations(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(pageOutput.getContent(), responseHeader, HttpStatus.OK);
    }

    @GetMapping("org/{id}")
    public ResponseEntity<?> getOrganization(@PathVariable("id") Long id) {

        Organization output = orgService.getOrganization(id);
        if (output != null){
            return ResponseEntity.ok(output);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }

    @PostMapping("org")
    public ResponseEntity<?> addEvent(@RequestBody Organization org){
        Organization output = orgService.save(org);
        return ResponseEntity.ok(org);
    }
}
