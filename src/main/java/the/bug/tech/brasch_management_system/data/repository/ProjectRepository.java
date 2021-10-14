package the.bug.tech.brasch_management_system.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import the.bug.tech.brasch_management_system.model.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("SELECT p FROM Project p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))")
    Project findProjectByNameContainsIgnoreCase(@Param("projectName") String projectName);

    @Query("SELECT p FROM Project p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectAddress, '%'))")
    Project findProjectByAddressContainsIgnoreCase(@Param("projectAddress") String projectAddress);

    @Query("SELECT p FROM Project p JOIN FETCH p.company AS c WHERE UPPER(c.companyName) LIKE UPPER(concat('%', :companyName, '%'))")
    List<Project> findProjectByCompanyContainsIgnoreCase(@Param("companyName") String companyName);

    @Query("SELECT p FROM Project p JOIN FETCH p.projectManager AS pm WHERE UPPER(CONCAT(pm.projectManagerPerson.firstName, " +
            "pm.projectManagerPerson.lastName)) LIKE UPPER(CONCAT('%',:name,'%'))")
    List<Project> findProjectByProjectManagerContainsIgnoreCase(@Param("name") String name);

    @Query("SELECT p FROM Project p JOIN FETCH p.contactPerson AS cp WHERE UPPER(CONCAT(cp.contactPersonPerson.firstName, cp.contactPersonPerson.lastName)) LIKE UPPER(CONCAT('%', :name ,'%'))")
    List<Project> findProjectByContactPersonContainsIgnoreCase(@Param("name") String name);


}
