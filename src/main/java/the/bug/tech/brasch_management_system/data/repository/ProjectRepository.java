package the.bug.tech.brasch_management_system.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import the.bug.tech.brasch_management_system.model.Project;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, String> {

    @Query("SELECT p FROM Project p WHERE p.projectName = (:projectName)")
    Project findProjectByName (@Param("projectName") String projectName);

    @Query("SELECT p FROM Project p WHERE p.projectLocal = (:projectAddress)")
    Project findProjectByAddress(@Param("projectAddress") String projectAddress);

    @Query("SELECT p FROM Project p JOIN FETCH p.company AS c WHERE UPPER(c.companyName) = UPPER(:companyName)")
    List<Project> findProjectByCompany(@Param("companyName") String companyName);

    @Query("SELECT p FROM Project p JOIN FETCH p.projectManager AS pm WHERE UPPER(CONCAT(pm.projectManagerPerson.firstName, " +
            "pm.projectManagerPerson.lastName)) = UPPER(:name)")
    List<Project> findProjectByProjectManager(@Param("name") String name);

    @Query("SELECT p FROM Project p JOIN FETCH p.contactPerson AS cp WHERE UPPER(CONCAT(cp.contactPersonPerson.firstName, cp.contactPersonPerson.lastName)) = UPPER(:name)")
    List<Project> findProjectByContactPerson(@Param("name") String name);


}
