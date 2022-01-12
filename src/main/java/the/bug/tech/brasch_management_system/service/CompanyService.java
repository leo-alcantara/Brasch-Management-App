package the.bug.tech.brasch_management_system.service;

import io.vavr.control.Option;
import the.bug.tech.brasch_management_system.model.Company;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface CompanyService {

    CompletionStage<Company> insertCompany(Company company);

    CompletionStage<Company> getCompanyById(String companyId);

    CompletionStage<List<Company>> getAllCompanies();

    CompletionStage<Company> updateCompany(Company company);

    CompletionStage<Option<Void>> deleteCompany(Company company);

    CompletionStage<Option<Company>> getCompanyByCompanyNameContainsIgnoreCase(String companyName);

    CompletionStage<Option<Company>> getCompanyByProjectNameContainsIgnoreCase(String projectName);

    CompletionStage<Option<Company>> getCompanyByContactPersonContainsIgnoreCase(String name);
}
