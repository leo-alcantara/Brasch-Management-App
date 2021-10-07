package the.bug.tech.brasch_management_system.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import the.bug.tech.brasch_management_system.model.ContactPerson;

import java.util.List;

public interface ContactPersonRepository extends CrudRepository<ContactPerson, String> {

    @Query("SELECT cp FROM ContactPerson cp JOIN FETCH cp.contactPersonPerson AS p WHERE UPPER(CONCAT(p.firstName, p.lastName)) = UPPER(:contactPersonName)")
    ContactPerson findContactPersonByName(@Param("contactPersonName") String contactPersonName);

    @Query("SELECT cp FROM ContactPerson cp JOIN FETCH cp.company AS c WHERE UPPER(c.companyName) = UPPER(:companyName)")
    List<ContactPerson> findContactPersonByCompany(@Param("companyName") String companyName);

    @Query("SELECT cp FROM ContactPerson cp JOIN FETCH cp.projectList AS p WHERE UPPER(p.projectName) = UPPER(:projectName)")
    List<ContactPerson> findContactPersonByProject(@Param("projectName") String projectName);




}
