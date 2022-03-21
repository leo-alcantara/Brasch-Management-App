package the.bug.tech.brasch_management_system.service;

import the.bug.tech.brasch_management_system.model.ContactPerson;

import java.util.List;

public interface ContactPersonService {

    List<ContactPerson> getContactPersonByNameContainsIgnoreCase(String contactPersonName);

    List<ContactPerson> getContactPersonByCompanyContainsIgnoreCase(String companyName);

    List<ContactPerson> getContactPersonByProjectContainsIgnoreCase(String projectName);
}
