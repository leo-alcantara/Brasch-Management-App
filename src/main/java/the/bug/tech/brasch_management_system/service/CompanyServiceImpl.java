package the.bug.tech.brasch_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.model.Company;
import the.bug.tech.brasch_management_system.repository.CompanyRepositoryAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepositoryAsync COMPANY_REPOSITORY_ASYNC;

    @Autowired
    public CompanyServiceImpl(CompanyRepositoryAsync COMPANY_REPOSITORY_ASYNC) {
        this.COMPANY_REPOSITORY_ASYNC = COMPANY_REPOSITORY_ASYNC;
    }

    @Override
    @Transactional
    public CompletionStage<Company> insertCompany(Company company) {
        return COMPANY_REPOSITORY_ASYNC.insertCompany(company);
    }

    @Override
    public CompletionStage<Company> getCompanyById(String companyId) {
        return COMPANY_REPOSITORY_ASYNC.getCompanyById(companyId);
    }

    @Override
    public CompletionStage<List<Company>> getAllCompanies() {
        return COMPANY_REPOSITORY_ASYNC.getAllCompanies();
    }

    @Override
    @Transactional
    public CompletionStage<Company> updateCompany(Company company) {
        return COMPANY_REPOSITORY_ASYNC.updateCompany(company);
    }

    @Override
    @Transactional
    public CompletionStage<Void> deleteCompany(Company company) {
        return COMPANY_REPOSITORY_ASYNC.deleteCompany(company);
    }

    @Override
    public CompletionStage<Company> getCompanyByCompanyNameContainsIgnoreCase(String companyName) {
        return COMPANY_REPOSITORY_ASYNC.getCompanyByCompanyNameContainsIgnoreCase(companyName);
    }

    @Override
    public CompletionStage<Company> getCompanyByProjectNameContainsIgnoreCase(String projectName) {
        return COMPANY_REPOSITORY_ASYNC.getCompanyByProjectNameContainsIgnoreCase(projectName);
    }

    @Override
    public CompletionStage<Company> getCompanyByContactPersonContainsIgnoreCase(String name) {
        return COMPANY_REPOSITORY_ASYNC.getCompanyByContactPersonContainsIgnoreCase(name);
    }
}
