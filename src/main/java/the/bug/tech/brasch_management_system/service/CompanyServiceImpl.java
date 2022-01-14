package the.bug.tech.brasch_management_system.service;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.model.Company;
import the.bug.tech.brasch_management_system.repository.CompanyRepositoryAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepositoryAsync companyRepositoryAsync;

    @Autowired
    public CompanyServiceImpl(CompanyRepositoryAsync companyRepositoryAsync) {
        this.companyRepositoryAsync = companyRepositoryAsync;
    }

    @Override
    @Transactional
    public CompletionStage<Company> insertCompany(Company company) {
        return companyRepositoryAsync.insertCompany(company);
    }

    @Override
    public CompletionStage<Company> getCompanyById(String companyId) {
        return companyRepositoryAsync.getCompanyById(companyId);
    }

    @Override
    public CompletionStage<List<Company>> getAllCompanies() {
        return companyRepositoryAsync.getAllCompanies();
    }

    @Override
    @Transactional
    public CompletionStage<Company> updateCompany(Company company) {
        return companyRepositoryAsync.updateCompany(company);
    }

    @Override
    @Transactional
    public CompletionStage<Void> deleteCompany(Company company) {
        return companyRepositoryAsync.deleteCompany(company);
    }

    @Override
    public CompletionStage<Company> getCompanyByCompanyNameContainsIgnoreCase(String companyName) {
        return companyRepositoryAsync.getCompanyByCompanyNameContainsIgnoreCase(companyName);
    }

    @Override
    public CompletionStage<Company> getCompanyByProjectNameContainsIgnoreCase(String projectName) {
        return companyRepositoryAsync.getCompanyByProjectNameContainsIgnoreCase(projectName);
    }

    @Override
    public CompletionStage<Company> getCompanyByContactPersonContainsIgnoreCase(String name) {
        return companyRepositoryAsync.getCompanyByContactPersonContainsIgnoreCase(name);
    }
}
