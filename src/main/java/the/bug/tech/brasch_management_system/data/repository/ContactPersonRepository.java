package the.bug.tech.brasch_management_system.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import the.bug.tech.brasch_management_system.model.ContactPerson;

import java.util.List;

public interface ContactPersonRepository extends JpaRepository<ContactPerson, String> {

    @Query("SELECT cp FROM ContactPerson cp JOIN FETCH cp.contactPersonPerson AS p WHERE UPPER(CONCAT(p.firstName, p.lastName)) LIKE UPPER(CONCAT('%', :contactPersonName, '%') )")
    ContactPerson findContactPersonByNameContainsIgnoreCase(@Param("contactPersonName") String contactPersonName);

    @Query("SELECT cp FROM ContactPerson cp JOIN FETCH cp.company AS c WHERE UPPER(c.companyName) LIKE UPPER(CONCAT('%', :companyName, '%'))")
    List<ContactPerson> findContactPersonByCompanyContainsIgnoreCase(@Param("companyName") String companyName);

    @Query("SELECT cp FROM ContactPerson cp JOIN FETCH cp.projectList AS p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))")
    List<ContactPerson> findContactPersonByProjectContainsIgnoreCase(@Param("projectName") String projectName);




}
