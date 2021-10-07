package the.bug.tech.brasch_management_system.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import the.bug.tech.brasch_management_system.model.Company;

public interface CompanyRepository extends CrudRepository<Company, String> {

    @Query("SELECT c FROM Company c WHERE c.companyName = (:companyName)")
    Company findCompanyByCompanyName(@Param("companyName") String companyName);

    @Query("SELECT c FROM Company c JOIN FETCH c.projectsList AS p WHERE UPPER(p.projectName) = UPPER(:projectName) ")
    Company findCompanyByProjectName(@Param("projectName") String projectName);

    @Query("SELECT c FROM Company c JOIN FETCH c.contactPersonList AS cp WHERE " +
            "UPPER(CONCAT(cp.contactPersonPerson.firstName, cp.contactPersonPerson.lastName))  = UPPER(:name)")
    Company findCompanyByContactPerson(@Param("name") String name);

}
