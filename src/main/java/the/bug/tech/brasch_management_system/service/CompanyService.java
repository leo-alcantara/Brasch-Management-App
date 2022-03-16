package the.bug.tech.brasch_management_system.service;

import the.bug.tech.brasch_management_system.model.Company;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface CompanyService {

    CompletionStage<Company> insertCompany(Company company);

    CompletionStage<Company> getCompanyById(Integer companyId);

    CompletionStage<List<Company>> getAllCompanies();

    CompletionStage<Company> updateCompany(Company company);

    CompletionStage<Void> deleteCompany(Company company);

    CompletionStage<List<Company>> getCompanyByCompanyNameContainsIgnoreCase(String companyName);

    CompletionStage<List<Company>> getCompanyByProjectNameContainsIgnoreCase(String projectName);

    CompletionStage<List<Company>> getCompanyByContactPersonContainsIgnoreCase(String name);
}
