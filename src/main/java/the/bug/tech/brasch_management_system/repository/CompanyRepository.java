package the.bug.tech.brasch_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import the.bug.tech.brasch_management_system.model.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("SELECT c FROM Company c WHERE UPPER(c.companyName) LIKE UPPER(CONCAT('%', :companyName, '%'))")
    List<Company> getCompanyByCompanyNameContainsIgnoreCase(@Param("companyName") String companyName);

    @Query("SELECT c FROM Company c JOIN FETCH c.projectsList AS p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))")
    List<Company> getCompanyByProjectNameContainsIgnoreCase(@Param("projectName") String projectName);

    @Query("SELECT c FROM Company c JOIN FETCH c.contactPersonList AS cp WHERE " +
            "UPPER(CONCAT(cp.contactPersonPerson.firstName, cp.contactPersonPerson.lastName)) LIKE UPPER(CONCAT('%', :contactPersonName, '%'))")
    List<Company> getCompanyByContactPersonContainsIgnoreCase(@Param("contactPersonName") String contactPersonName);

}
