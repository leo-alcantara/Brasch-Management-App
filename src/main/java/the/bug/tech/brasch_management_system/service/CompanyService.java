package the.bug.tech.brasch_management_system.service;

import the.bug.tech.brasch_management_system.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanyByCompanyNameContainsIgnoreCase(String companyName);

    List<Company> getCompanyByProjectNameContainsIgnoreCase(String projectName);

    List<Company> getCompanyByContactPersonContainsIgnoreCase(String contactPersonName);
}
