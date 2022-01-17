package the.bug.tech.brasch_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import the.bug.tech.brasch_management_system.model.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("SELECT p FROM Project p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))")
    Project getProjectByNameContainsIgnoreCase(@Param("projectName") String projectName);

    @Query("SELECT p FROM Project p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectAddress, '%'))")
    Project getProjectByAddressContainsIgnoreCase(@Param("projectAddress") String projectAddress);

    @Query("SELECT p FROM Project p JOIN FETCH p.company AS c WHERE UPPER(c.companyName) LIKE UPPER(concat('%', :companyName, '%'))")
    List<Project> getProjectByCompanyContainsIgnoreCase(@Param("companyName") String companyName);

    @Query("SELECT p FROM Project p JOIN FETCH p.projectManager AS pm WHERE UPPER(CONCAT(pm.projectManagerPerson.firstName, " +
            "pm.projectManagerPerson.lastName)) LIKE UPPER(CONCAT('%',:projectManagerName,'%'))")
    List<Project> getProjectByProjectManagerContainsIgnoreCase(@Param("projectManagerName") String projectManagerName);

    @Query("SELECT p FROM Project p JOIN FETCH p.contactPersonList AS cp WHERE UPPER(CONCAT(cp.contactPersonPerson.firstName, cp.contactPersonPerson.lastName)) LIKE UPPER(CONCAT('%', :contactPersonName ,'%'))")
    List<Project> getProjectByContactPersonContainsIgnoreCase(@Param("contactPersonName") String contactPersonName);


}
