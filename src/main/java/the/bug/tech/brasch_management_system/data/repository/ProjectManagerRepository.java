package the.bug.tech.brasch_management_system.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import the.bug.tech.brasch_management_system.model.ProjectManager;

public interface ProjectManagerRepository extends CrudRepository <ProjectManager, String > {

    @Query("SELECT pm FROM ProjectManager pm JOIN FETCH pm.projectManagerPerson AS " +
            "pmp WHERE UPPER(CONCAT(pmp.firstName, pmp.lastName)) = UPPER(:name)")
    ProjectManager findProjectManagerByName(@Param("name"), String name);

    @Query("SELECT pm FROM ProjectManager pm JOIN FETCH pm.projectList AS p WHERE UPPER(p.projectName) = UPPER(:projectName)")
    ProjectManager findProjectManagerByProject(@Param("projectName") String projectName);

}
