package the.bug.tech.brasch_management_system.repository;

import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import the.bug.tech.brasch_management_system.model.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {

    @Query("SELECT p FROM Project p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))")
    Option<Project> getProjectByNameContainsIgnoreCase(@Param("projectName") String projectName);

    @Query("SELECT p FROM Project p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectAddress, '%'))")
    Option<Project> getProjectByAddressContainsIgnoreCase(@Param("projectAddress") String projectAddress);

    @Query("SELECT p FROM Project p JOIN FETCH p.company AS c WHERE UPPER(c.companyName) LIKE UPPER(concat('%', :companyName, '%'))")
    List<Project> getProjectByCompanyContainsIgnoreCase(@Param("companyName") String companyName);

    @Query("SELECT p FROM Project p JOIN FETCH p.projectManager AS pm WHERE UPPER(CONCAT(pm.projectManagerPerson.firstName, " +
            "pm.projectManagerPerson.lastName)) LIKE UPPER(CONCAT('%',:name,'%'))")
    List<Project> getProjectByProjectManagerContainsIgnoreCase(@Param("projectManagerName") String projectManagerName);

    @Query("SELECT p FROM Project p JOIN FETCH p.contactPerson AS cp WHERE UPPER(CONCAT(cp.contactPersonPerson.firstName, cp.contactPersonPerson.lastName)) LIKE UPPER(CONCAT('%', :name ,'%'))")
    List<Project> getProjectByContactPersonContainsIgnoreCase(@Param("contactPersonName") String contactPersonName);


}
