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

    private final CompanyRepository COMPANY_REPOSITORY;
    private final Executor EXECUTOR;
    @PersistenceContext
    private final EntityManager ENTITY_MANAGER;

    public CompanyRepositoryAsync(CompanyRepository COMPANY_REPOSITORY, Executor EXECUTOR, EntityManager ENTITY_MANAGER) {
        this.COMPANY_REPOSITORY = COMPANY_REPOSITORY;
        this.EXECUTOR = EXECUTOR;
        this.ENTITY_MANAGER = ENTITY_MANAGER;
    }

    public CompletionStage<Company> insertCompany(Company company) {
        return CompletableFuture.supplyAsync(() -> COMPANY_REPOSITORY.save(company), EXECUTOR);
    }

    public CompletionStage<Company> getCompanyById(String companyId) {
        return CompletableFuture.supplyAsync(() -> COMPANY_REPOSITORY.getById(companyId), EXECUTOR);
    }

    public CompletionStage<List<Company>> getAllCompanies() {
        return CompletableFuture.supplyAsync(() -> COMPANY_REPOSITORY.findAll(), EXECUTOR);
    }

    public CompletionStage<Void> deleteCompany(Company company) {
        return CompletableFuture.supplyAsync(() -> {
            COMPANY_REPOSITORY.delete(company);
            return null;
        }, EXECUTOR);
    }

    public CompletionStage<Company> updateCompany(Company company) {
        return CompletableFuture.supplyAsync(() -> ENTITY_MANAGER.merge(company), EXECUTOR);
    }

    public CompletionStage<Company> getCompanyByCompanyNameContainsIgnoreCase(String companyName) {
        return CompletableFuture.supplyAsync(() -> COMPANY_REPOSITORY.getCompanyByCompanyNameContainsIgnoreCase(companyName), EXECUTOR);
    }

    public CompletionStage<Company> getCompanyByProjectNameContainsIgnoreCase(String projectName) {
        return CompletableFuture.supplyAsync(() -> COMPANY_REPOSITORY.getCompanyByProjectNameContainsIgnoreCase(projectName), EXECUTOR);
    }

    public CompletionStage<Company> getCompanyByContactPersonContainsIgnoreCase(String contactPersonName) {
        return CompletableFuture.supplyAsync(() -> COMPANY_REPOSITORY.getCompanyByContactPersonContainsIgnoreCase(contactPersonName), EXECUTOR);
    }
}
