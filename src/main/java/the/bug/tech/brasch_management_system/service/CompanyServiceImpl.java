package the.bug.tech.brasch_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.exceptions.ResourceNotFoundException;
import the.bug.tech.brasch_management_system.model.Company;
import the.bug.tech.brasch_management_system.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Transactional
    public Company insertCompany(Company company) {
        companyRepository.save(company);
        return company;
    }

    @Transactional
    public Company getCompanyById(Integer companyId) {
        Optional<Company> foundCompany = companyRepository.findById(companyId);

        if (foundCompany.isPresent()) {
            return foundCompany.get();
        } else {
            throw new ResourceNotFoundException("Could not find Company with id " + companyId);
        }
    }

    @Transactional
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Transactional
    public Company updateCompany(Integer companyId, Company company) {

        Optional<Company> original = companyRepository.findById(companyId);

        if(original.isPresent()) {
            original.get().setCompanyName(company.getCompanyName());
            original.get().setCompanyAddress(company.getCompanyAddress());
            original.get().setCompanyPhoneNumber(company.getCompanyPhoneNumber());
            original.get().setContactPersonList(company.getContactPersonList());
            original.get().setProjectsList(company.getProjectsList());
            return original.get();
        } else {
            throw new ResourceNotFoundException("Could not update Company with id " + companyId);
        }
    }

    @Transactional
    public void deleteCompanyById(Integer companyId) {
        companyRepository.deleteById(companyId);
    }

    @Override
    @Transactional
    public List<Company> getCompanyByCompanyNameContainsIgnoreCase(String companyName) {
        return companyRepository.getCompanyByCompanyNameContainsIgnoreCase(companyName);
    }

    @Override
    @Transactional
    public List<Company> getCompanyByProjectNameContainsIgnoreCase(String projectName) {
        return companyRepository.getCompanyByProjectNameContainsIgnoreCase(projectName);
    }

    @Override
    @Transactional
    public List<Company> getCompanyByContactPersonContainsIgnoreCase(String contactPersonName) {
        return companyRepository.getCompanyByContactPersonContainsIgnoreCase(contactPersonName);
    }
}
