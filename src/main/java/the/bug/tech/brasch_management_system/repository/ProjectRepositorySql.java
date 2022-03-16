package the.bug.tech.brasch_management_system.repository;

import the.bug.tech.brasch_management_system.model.Project;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ProjectRepositorySql implements ProjectRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String FIND_PROJECT_BY_NAME =
            "SELECT p FROM Project p " +
                    "WHERE UPPER(p.projectName) " +
                    "LIKE UPPER(CONCAT('%', :projectName, '%'))";

    private static final String FIND_PROJECT_BY_ADDRESS =
            "SELECT p FROM Project p " +
                    "WHERE UPPER(p.projectName) " +
                    "LIKE UPPER(CONCAT('%', :projectAddress, '%'))";

    private static final String FIND_PROJECT_BY_COMPANY =
            "SELECT p FROM Project p " +
                    "JOIN FETCH p.company " +
                    "AS c WHERE UPPER(c.companyName) " +
                    "LIKE UPPER(concat('%', :companyName, '%'))";

    private static final String FIND_PROJECT_BY_MANAGER =
            "SELECT p FROM Project p " +
                    "JOIN FETCH p.projectManager " +
                    "AS pm WHERE UPPER(CONCAT(pm.projectManagerPerson.firstName, " +
                    "pm.projectManagerPerson.lastName)) " +
                    "LIKE UPPER(CONCAT('%',:projectManagerName,'%'))";

    private static final String FIND_PROJECT_BY_CONTACT_PERSON =
            "SELECT p FROM Project p " +
                    "JOIN FETCH p.contactPersonList " +
                    "AS cp " +
                    "WHERE UPPER(CONCAT(cp.contactPersonPerson.firstName, cp.contactPersonPerson.lastName)) " +
                    "LIKE UPPER(CONCAT('%', :contactPersonName ,'%'))";

    private static final String FIND_ALL_PROJECTS =
            "SELECT p FROM Project p";

    @Override
    public Project create(Project project) {
        entityManager.persist(project);
        return project;
    }

    @Override
    public Project delete(Project project) {
        entityManager.remove(project);
        return project;
    }

    @Override
    public List<Project> findAll() {
        return entityManager.createQuery(FIND_ALL_PROJECTS, Project.class)
                .getResultList();
    }

    @Override
    public Project findById(Integer projectId) {
        return entityManager.find(Project.class, projectId);
    }

    @Override
    public Project update(Project project) {
        return entityManager.merge(project);
    }

    @Override
    public void clear() {
        entityManager.clear();
    }

    @Override
    public List<Project> getProjectByNameContainsIgnoreCase(String projectName) {
        if (projectName.isEmpty()) {
            return findAll();
        }
        return entityManager.createQuery(FIND_PROJECT_BY_NAME, Project.class)
                .setParameter("projectName", projectName)
                .getResultList();
    }

    @Override
    public List<Project> getProjectByAddressContainsIgnoreCase(String projectAddress) {
        if (projectAddress.isEmpty()) {
            return findAll();
        }
        return entityManager.createQuery(FIND_PROJECT_BY_ADDRESS, Project.class)
                .setParameter("projectAddress", projectAddress)
                .getResultList();
    }

    @Override
    public List<Project> getProjectByCompanyContainsIgnoreCase(String companyName) {
        if (companyName.isEmpty()) {
            return findAll();
        }
        return entityManager.createQuery(FIND_PROJECT_BY_COMPANY, Project.class)
                .setParameter("companyName", companyName)
                .getResultList();
    }

    @Override
    public List<Project> getProjectByProjectManagerContainsIgnoreCase(String projectManagerName) {
        if (projectManagerName.isEmpty()) {
            return findAll();
        }
        return entityManager.createQuery(FIND_PROJECT_BY_MANAGER, Project.class)
                .setParameter("projectManagerName", projectManagerName)
                .getResultList();
    }

    @Override
    public List<Project> getProjectByContactPersonContainsIgnoreCase(String contactPersonName) {
        if (contactPersonName.isEmpty()) {
            return findAll();
        }
        return entityManager.createQuery(FIND_PROJECT_BY_CONTACT_PERSON, Project.class)
                .setParameter("contactPersonName", contactPersonName)
                .getResultList();
    }
}
