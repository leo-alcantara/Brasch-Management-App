package the.bug.tech.brasch_management_system.repository;

import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import the.bug.tech.brasch_management_system.model.ContactPerson;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Repository
public interface ContactPersonRepository extends JpaRepository<ContactPerson, String> {

    @Query("SELECT cp FROM ContactPerson cp JOIN FETCH cp.contactPersonPerson AS p WHERE UPPER(CONCAT(p.firstName, p.lastName)) LIKE UPPER(CONCAT('%', :contactPersonName, '%') )")
    Option<ContactPerson> getContactPersonByNameContainsIgnoreCase(@Param("contactPersonName") String contactPersonName);

    @Query("SELECT cp FROM ContactPerson cp JOIN FETCH cp.company AS c WHERE UPPER(c.companyName) LIKE UPPER(CONCAT('%', :companyName, '%'))")
    List<ContactPerson> getContactPersonByCompanyContainsIgnoreCase(@Param("companyName") String companyName);

    @Query("SELECT cp FROM ContactPerson cp JOIN FETCH cp.projectList AS p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))")
    List<ContactPerson> getContactPersonByProjectContainsIgnoreCase(@Param("projectName") String projectName);




}
