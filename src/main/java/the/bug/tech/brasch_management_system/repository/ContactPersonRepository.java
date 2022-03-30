package the.bug.tech.brasch_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import the.bug.tech.brasch_management_system.model.ContactPerson;

import java.util.List;

public interface ContactPersonRepository extends JpaRepository<ContactPerson, Integer> {

    @Query("SELECT cp FROM ContactPerson cp JOIN Person p ON cp.contactPersonPerson.personId = p.personId " +
            "WHERE UPPER(p.personName) LIKE UPPER(CONCAT('%', :contactPersonName, '%') )")
    List<ContactPerson> getContactPersonByNameContainsIgnoreCase(@Param("contactPersonName") String contactPersonName);

    @Query("SELECT cp FROM ContactPerson cp JOIN Company c ON cp.company.companyId = c.companyId " +
            "WHERE UPPER(c.companyName) LIKE UPPER(CONCAT('%', :companyName, '%'))")
    List<ContactPerson> getContactPersonByCompanyContainsIgnoreCase(@Param("companyName") String companyName);

    @Query("SELECT cp FROM ContactPerson cp JOIN cp.projectList AS p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))")
    List<ContactPerson> getContactPersonByProjectContainsIgnoreCase(@Param("projectName") String projectName);


}
