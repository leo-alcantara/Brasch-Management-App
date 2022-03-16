package the.bug.tech.brasch_management_system.repository;

import org.springframework.stereotype.Repository;
import the.bug.tech.brasch_management_system.model.Company;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

@Repository
public class CompanyRepositoryAsync {

    private final CompanyRepository companyRepository;
    private final Executor executor;

    public CompanyRepositoryAsync(CompanyRepository companyRepository, Executor executor) {
        this.companyRepository = companyRepository;
        this.executor = executor;
    }

    public CompletionStage<Company> insertCompany(Company company) {
        return CompletableFuture.supplyAsync(() -> companyRepository.create(company), executor);
    }

    public CompletionStage<Company> getCompanyById(Integer companyId) {
        return CompletableFuture.supplyAsync(() -> companyRepository.findById(companyId), executor);
    }

    public CompletionStage<List<Company>> getAllCompanies() {
        return CompletableFuture.supplyAsync(() -> companyRepository.findAll(), executor);
    }

    public CompletionStage<Void> deleteCompany(Company company) {
        return CompletableFuture.runAsync(() ->
            companyRepository.delete(company), executor);
    }

    public CompletionStage<Company> updateCompany(Company company) {
        return CompletableFuture.supplyAsync(() -> companyRepository.update(company), executor);
    }

    public CompletionStage<List<Company>> getCompanyByCompanyNameContainsIgnoreCase(String companyName) {
        return CompletableFuture.supplyAsync(() -> companyRepository.getCompanyByCompanyNameContainsIgnoreCase(companyName), executor);
    }

    public CompletionStage<List<Company>> getCompanyByProjectNameContainsIgnoreCase(String projectName) {
        return CompletableFuture.supplyAsync(() -> companyRepository.getCompanyByProjectNameContainsIgnoreCase(projectName), executor);
    }

    public CompletionStage<List<Company>> getCompanyByContactPersonContainsIgnoreCase(String contactPersonName) {
        return CompletableFuture.supplyAsync(() -> companyRepository.getCompanyByContactPersonContainsIgnoreCase(contactPersonName), executor);
    }
}
