package the.bug.tech.brasch_management_system.repository;

import io.vavr.control.Option;
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
    @PersistenceContext
    private final EntityManager entityManager;

    public CompanyRepositoryAsync(CompanyRepository companyRepository, Executor executor, EntityManager entityManager) {
        this.companyRepository = companyRepository;
        this.executor = executor;
        this.entityManager = entityManager;
    }

    public CompletionStage<Company> insertCompany(Company company) {
        return CompletableFuture.supplyAsync(() -> companyRepository.save(company), executor);
    }

    public CompletionStage<Company> getCompanyById(String companyId) {
        return CompletableFuture.supplyAsync(() -> companyRepository.getById(companyId), executor);
    }

    public CompletionStage<List<Company>> getAllCompanies() {
        return CompletableFuture.supplyAsync(() -> companyRepository.findAll(), executor);
    }

    public CompletionStage<Option<Void>> deleteCompany(Company company) {
        return CompletableFuture.supplyAsync(() -> {
            companyRepository.delete(company);
            return null;
        }, executor);
    }

    public CompletionStage<Company> updateCompany(Company company) {
        return CompletableFuture.supplyAsync(() -> entityManager.merge(company), executor);
    }

    public CompletionStage<Option<Company>> getCompanyByCompanyNameContainsIgnoreCase(String companyName) {
        return CompletableFuture.supplyAsync(() -> companyRepository.getCompanyByCompanyNameContainsIgnoreCase(companyName), executor);
    }

    public CompletionStage<Option<Company>> getCompanyByProjectNameContainsIgnoreCase(String projectName) {
        return CompletableFuture.supplyAsync(() -> companyRepository.getCompanyByProjectNameContainsIgnoreCase(projectName), executor);
    }

    public CompletionStage<Option<Company>> getCompanyByContactPersonContainsIgnoreCase(String contactPersonName) {
        return CompletableFuture.supplyAsync(() -> companyRepository.getCompanyByContactPersonContainsIgnoreCase(contactPersonName), executor);
    }
}
