package the.bug.tech.brasch_management_system.repository;

import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import the.bug.tech.brasch_management_system.model.ProjectManager;

import java.util.concurrent.CompletionStage;

public interface ProjectManagerRepository extends JpaRepository<ProjectManager, String > {

    @Query("SELECT pm FROM ProjectManager pm JOIN FETCH pm.projectManagerPerson AS " +
            "pmp WHERE UPPER(CONCAT(pmp.firstName, pmp.lastName)) LIKE UPPER(CONCAT('%', :name, '%'))")
    Option<ProjectManager> getProjectManagerByNameContainsIgnoreCase(@Param("projectManagerName") String projectManagerName);

    @Query("SELECT pm FROM ProjectManager pm JOIN FETCH pm.projectList AS p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))")
    Option<ProjectManager> getProjectManagerByProjectContainsIgnoreCase(@Param("projectName") String projectName);

}
