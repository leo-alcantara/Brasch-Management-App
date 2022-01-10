package the.bug.tech.brasch_management_system.repository;

import io.vavr.control.Option;
import org.springframework.stereotype.Repository;
import the.bug.tech.brasch_management_system.model.Company;

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

    public CompletionStage<Option<Company>> getCompanyByCompanyNameContainsIgnoreCase (String companyName){
        return CompletableFuture.supplyAsync(() -> companyRepository.getCompanyByCompanyNameContainsIgnoreCase(companyName), executor);
    }

    public CompletionStage<Option<Company>> getCompanyByProjectNameContainsIgnoreCase(String projectName){
        return CompletableFuture.supplyAsync(() -> companyRepository.getCompanyByProjectNameContainsIgnoreCase(projectName), executor);
    }

    public CompletionStage<List<Company>> getCompanyByContactPersonContainsIgnoreCase (String contactPersonName){
        return CompletableFuture.supplyAsync(() -> companyRepository.getCompanyByContactPersonContainsIgnoreCase(contactPersonName), executor);
    }














}
