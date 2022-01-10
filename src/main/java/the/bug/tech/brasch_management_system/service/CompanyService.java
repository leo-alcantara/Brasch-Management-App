package the.bug.tech.brasch_management_system.service;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.repository.CompanyRepository;
import the.bug.tech.brasch_management_system.model.Company;
import the.bug.tech.brasch_management_system.repository.CompanyRepositoryAsync;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyRepositoryAsync companyRepositoryAsync;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, CompanyRepositoryAsync companyRepositoryAsync) {
        this.companyRepository = companyRepository;
        this.companyRepositoryAsync = companyRepositoryAsync;
    }

    @Async
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Async
    public Company findById(String companyId) {
        Optional<Company> foundById = companyRepository.findById(companyId);
        return foundById.orElseThrow(IllegalArgumentException::new);
    }

    @Async
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Async
    @Transactional
    public Company update(Company company){
        Company original=companyRepository.findById(company.getCompanyId()).get();
        original.setCompanyName(company.getCompanyName());
        original.setCompanyAddress(company.getCompanyAddress());
        original.setCompanyPhoneNumber(company.getCompanyPhoneNumber());
        original.setProjectsList(company.getProjectsList());
        original.setContactPersonList(company.getContactPersonList());

        return original;
    }

    @Async
    public void delete(String companyId){
        companyRepository.deleteById(companyId);
    }

    public CompletionStage<Option<Company>> findCompanyByCompanyNameContainsIgnoreCase(String companyName) {
        return companyRepositoryAsync.getCompanyByCompanyNameContainsIgnoreCase(companyName);
    }

    public CompletionStage<Option<Company>> findCompanyByProjectNameContainsIgnoreCase(String projectName) {
        return companyRepositoryAsync.getCompanyByProjectNameContainsIgnoreCase(projectName);
    }

    public CompletionStage<List<Company>> findCompanyByContactPersonContainsIgnoreCase(String name){
       return companyRepositoryAsync.getCompanyByContactPersonContainsIgnoreCase(name);
    }
}
