package the.bug.tech.brasch_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import the.bug.tech.brasch_management_system.model.ProjectManager;

@Repository
public interface ProjectManagerRepository extends JpaRepository<ProjectManager, String> {

    @Query("SELECT pm FROM ProjectManager pm JOIN FETCH pm.projectManagerPerson AS " +
            "pmp WHERE UPPER(CONCAT(pmp.firstName, pmp.lastName)) LIKE UPPER(CONCAT('%', :projectManagerName, '%'))")
    ProjectManager getProjectManagerByNameContainsIgnoreCase(@Param("projectManagerName") String projectManagerName);

    @Query("SELECT pm FROM ProjectManager pm JOIN FETCH pm.projectList AS p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))")
    ProjectManager getProjectManagerByProjectContainsIgnoreCase(@Param("projectName") String projectName);

}
