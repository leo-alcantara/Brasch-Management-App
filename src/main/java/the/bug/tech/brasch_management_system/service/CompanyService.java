package the.bug.tech.brasch_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.data.repository.CompanyRepository;
import the.bug.tech.brasch_management_system.model.Company;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company findById(String companyId) {
        Optional<Company> foundById = companyRepository.findById(companyId);
        return foundById.orElseThrow(IllegalArgumentException::new);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

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

    public void delete(String companyId){
        companyRepository.deleteById(companyId);
    }

    public Company findCompanyByCompanyNameContainsIgnoreCase(String companyName) {
        return companyRepository.findCompanyByCompanyNameContainsIgnoreCase(companyName);
    }

    public Company findCompanyByProjectNameContainsIgnoreCase(String projectName) {
        return companyRepository.findCompanyByProjectNameContainsIgnoreCase(projectName);
    }

    public List<Company> findCompanyByContactPersonContainsIgnoreCase(String name){
       return companyRepository.findCompanyByContactPersonContainsIgnoreCase(name);
    }
}
