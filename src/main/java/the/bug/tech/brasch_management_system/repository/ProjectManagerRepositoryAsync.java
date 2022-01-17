package the.bug.tech.brasch_management_system.repository;

import org.springframework.stereotype.Repository;
import the.bug.tech.brasch_management_system.model.ProjectManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

@Repository
public class ProjectManagerRepositoryAsync {

    private final ProjectManagerRepository PROJECT_MANAGER_REPOSITORY;
    private final Executor EXECUTOR;

    @PersistenceContext
    private final EntityManager ENTITY_MANAGER;

    public ProjectManagerRepositoryAsync(ProjectManagerRepository PROJECT_MANAGER_REPOSITORY, Executor EXECUTOR, EntityManager ENTITY_MANAGER) {
        this.PROJECT_MANAGER_REPOSITORY = PROJECT_MANAGER_REPOSITORY;
        this.EXECUTOR = EXECUTOR;
        this.ENTITY_MANAGER = ENTITY_MANAGER;
    }

    public CompletionStage<ProjectManager> insertProjectManager(ProjectManager projectManager) {
        return CompletableFuture.supplyAsync(() -> PROJECT_MANAGER_REPOSITORY.save(projectManager), EXECUTOR);
    }

    public CompletionStage<ProjectManager> getProjectManagerById(String projectManagerId) {
        return CompletableFuture.supplyAsync(() -> PROJECT_MANAGER_REPOSITORY.getById(projectManagerId), EXECUTOR);
    }

    public CompletionStage<List<ProjectManager>> getAllProjectManagers() {
        return CompletableFuture.supplyAsync(() -> PROJECT_MANAGER_REPOSITORY.findAll(), EXECUTOR);
    }

    public CompletionStage<Void> deleteProjectManager(ProjectManager projectManager) {
        return CompletableFuture.supplyAsync(() -> {
            PROJECT_MANAGER_REPOSITORY.delete(projectManager);
            return null;
        }, EXECUTOR);
    }

    public CompletionStage<ProjectManager> updateProjectManager(ProjectManager projectManager) {
        return CompletableFuture.supplyAsync(() -> ENTITY_MANAGER.merge(projectManager), EXECUTOR);
    }

    public CompletionStage<ProjectManager> getProjectManagerByNameContainsIgnoreCase(String projectManagerName) {
        return CompletableFuture.supplyAsync(() -> PROJECT_MANAGER_REPOSITORY.getProjectManagerByNameContainsIgnoreCase(projectManagerName), EXECUTOR);
    }

    public CompletionStage<ProjectManager> getProjectManagerByProjectContainsIgnoreCase(String projectName) {
        return CompletableFuture.supplyAsync(() -> PROJECT_MANAGER_REPOSITORY.getProjectManagerByProjectContainsIgnoreCase(projectName), EXECUTOR);
    }
}
