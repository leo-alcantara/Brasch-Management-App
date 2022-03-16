package the.bug.tech.brasch_management_system.repository;

import the.bug.tech.brasch_management_system.model.ProjectManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProjectManagerRepositorySql implements ProjectManagerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String FIND_PROJECT_MANAGER_BY_NAME =
            "SELECT pm FROM ProjectManager pm JOIN FETCH pm.projectManagerPerson AS " +
                    "pmp WHERE UPPER(CONCAT(pmp.firstName, pmp.lastName)) LIKE UPPER(CONCAT('%', :projectManagerName, '%'))";

    private static final String FIND_PROJECT_MANAGER_BY_PROJECT =
            "SELECT pm FROM ProjectManager pm JOIN FETCH pm.projectList AS p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))";

    private static final String FIND_ALL_PROJECT_MANAGERS =
            "SELECT pm FROM ProjectManager pm";

    @Override
    public ProjectManager create(ProjectManager projectManager) {
        entityManager.persist(projectManager);
        return projectManager;
    }

    @Override
    public ProjectManager delete(ProjectManager projectManager) {
        entityManager.remove(projectManager);
        return projectManager;
    }

    @Override
    public List<ProjectManager> findAll() {
        return entityManager.createQuery(FIND_ALL_PROJECT_MANAGERS, ProjectManager.class)
                .getResultList();
    }

    @Override
    public ProjectManager findById(Integer projectManagerId) {
        return entityManager.find(ProjectManager.class, projectManagerId);
    }

    @Override
    public ProjectManager update(ProjectManager projectManager) {
        return entityManager.merge(projectManager);
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

    @Override
    public List<ProjectManager> getProjectManagerByNameContainsIgnoreCase(String projectManagerName) {
        if (projectManagerName.isEmpty()) {
            return findAll();
        }
        return entityManager.createQuery(FIND_PROJECT_MANAGER_BY_NAME, ProjectManager.class)
                .setParameter("projectManagerName", projectManagerName)
                .getResultList();
    }

    @Override
    public List<ProjectManager> getProjectManagerByProjectContainsIgnoreCase(String projectName) {
        if (projectName.isEmpty()) {
            return findAll();
        }
        return entityManager.createQuery(FIND_PROJECT_MANAGER_BY_PROJECT, ProjectManager.class)
                .setParameter("projectName", projectName)
                .getResultList();
    }
}
