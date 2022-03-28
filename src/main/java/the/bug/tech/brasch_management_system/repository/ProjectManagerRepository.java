package the.bug.tech.brasch_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import the.bug.tech.brasch_management_system.model.ProjectManager;

import java.util.List;


public interface ProjectManagerRepository extends JpaRepository<ProjectManager, Integer> {

    @Query("SELECT pm FROM ProjectManager pm JOIN FETCH pm.projectManagerPerson AS " +
            "pmp WHERE UPPER(pmp.personName) LIKE UPPER(CONCAT('%', :projectManagerName, '%'))")
    List<ProjectManager> getProjectManagerByNameContainsIgnoreCase(@Param("projectManagerName") String projectManagerName);

    @Query("SELECT pm FROM ProjectManager pm JOIN FETCH pm.projectList AS p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))")
    List<ProjectManager> getProjectManagerByProjectContainsIgnoreCase(@Param("projectName") String projectName);

}
