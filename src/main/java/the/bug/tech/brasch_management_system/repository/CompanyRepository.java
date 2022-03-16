package the.bug.tech.brasch_management_system.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import the.bug.tech.brasch_management_system.model.Company;

import java.util.List;

@Repository
public interface CompanyRepository extends GenericCRUDMethods<Company, Integer> {

    @Query("SELECT c FROM Company c WHERE UPPER(c.companyName) LIKE UPPER(CONCAT('%', :companyName, '%'))")
    List<Company> getCompanyByCompanyNameContainsIgnoreCase(@Param("companyName") String companyName);

    @Query("SELECT c FROM Company c JOIN FETCH c.projectsList AS p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))")
    List<Company> getCompanyByProjectNameContainsIgnoreCase(@Param("projectName") String projectName);

    @Query("SELECT c FROM Company c JOIN FETCH c.contactPersonList AS cp WHERE " +
            "UPPER(CONCAT(cp.contactPersonPerson.firstName, cp.contactPersonPerson.lastName)) LIKE UPPER(CONCAT('%', :contactPersonName, '%'))")
    List<Company> getCompanyByContactPersonContainsIgnoreCase(@Param("contactPersonName") String contactPersonName);

}
