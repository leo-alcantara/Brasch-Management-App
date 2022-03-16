package the.bug.tech.brasch_management_system.repository;

import the.bug.tech.brasch_management_system.model.Company;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class CompanyRepositorySql implements CompanyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String FIND_COMPANY_BY_NAME =
            "SELECT c FROM Company c WHERE UPPER(c.companyName) LIKE UPPER(CONCAT('%', :companyName, '%'))";

    private static final String FIND_COMPANY_BY_PROJECT_NAME =
            "SELECT c FROM Company c JOIN FETCH c.projectsList AS p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))";

    private static final String FIND_COMPANY_BY_CONTACT_PERSON =
            "SELECT c FROM Company c JOIN FETCH c.contactPersonList AS cp WHERE " +
                    "UPPER(CONCAT(cp.contactPersonPerson.firstName, cp.contactPersonPerson.lastName)) LIKE UPPER(CONCAT('%', :contactPersonName, '%'))";

    private static final String FIND_ALL_COMPANIES =
            "SELECT c FROM Company c";


    @Override
    @Transactional
    public List<Company> getCompanyByCompanyNameContainsIgnoreCase(String companyName) {
        if (companyName.isEmpty()) {
            return findAll();
        }
        return entityManager.createQuery(FIND_COMPANY_BY_NAME, Company.class)
                .setParameter("companyName", companyName)
                .getResultList();
    }

    @Override
    @Transactional
    public List<Company> getCompanyByProjectNameContainsIgnoreCase(String projectName) {
        if (projectName.isEmpty()) {
            return findAll();
        }
        return entityManager.createQuery(FIND_COMPANY_BY_PROJECT_NAME, Company.class)
                .setParameter("projectName", projectName).getResultList();
    }

    @Override
    @Transactional
    public List<Company> getCompanyByContactPersonContainsIgnoreCase(String contactPersonName) {
        return entityManager.createQuery(FIND_COMPANY_BY_CONTACT_PERSON, Company.class)
                .setParameter("contactPersonName", contactPersonName)
                .getResultList();
    }

    @Override
    @Transactional
    public Company create(Company company) {
        entityManager.persist(company);
        return company;
    }

    @Override
    @Transactional
    public Company delete(Company company) {
        entityManager.remove(company);
        return company;
    }

    @Override
    @Transactional
    public List<Company> findAll() {
        return entityManager.createQuery(FIND_ALL_COMPANIES, Company.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Company findById(Integer companyId) {
        return entityManager.find(Company.class, companyId);
    }

    @Override
    @Transactional
    public Company update(Company company) {
        return entityManager.merge(company);
    }

    @Override
    @Transactional
    public void clear() {
        entityManager.clear();
    }
}
