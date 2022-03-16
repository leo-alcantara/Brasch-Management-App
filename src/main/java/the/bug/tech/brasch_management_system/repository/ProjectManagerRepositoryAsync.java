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

    private final ProjectManagerRepository projectManagerRepository;
    private final Executor executor;
    @PersistenceContext
    private final EntityManager entityManager;

    public ProjectManagerRepositoryAsync(ProjectManagerRepository projectManagerRepository, Executor executor, EntityManager entityManager) {
        this.projectManagerRepository = projectManagerRepository;
        this.executor = executor;
        this.entityManager = entityManager;
    }

    public CompletionStage<ProjectManager> insertProjectManager(ProjectManager projectManager) {
        return CompletableFuture.supplyAsync(() -> projectManagerRepository.create(projectManager), executor);
    }

    public CompletionStage<ProjectManager> getProjectManagerById(Integer projectManagerId) {
        return CompletableFuture.supplyAsync(() -> projectManagerRepository.findById(projectManagerId), executor);
    }

    public CompletionStage<List<ProjectManager>> getAllProjectManagers() {
        return CompletableFuture.supplyAsync(() -> projectManagerRepository.findAll(), executor);
    }

    public CompletionStage<Void> deleteProjectManager(ProjectManager projectManager) {
        return CompletableFuture.runAsync(() -> projectManagerRepository.delete(projectManager), executor);
    }

    public CompletionStage<ProjectManager> updateProjectManager(ProjectManager projectManager) {
        return CompletableFuture.supplyAsync(() -> entityManager.merge(projectManager), executor);
    }

    public CompletionStage<List<ProjectManager>> getProjectManagerByNameContainsIgnoreCase(String projectManagerName) {
        return CompletableFuture.supplyAsync(() -> projectManagerRepository.getProjectManagerByNameContainsIgnoreCase(projectManagerName), executor);
    }

    public CompletionStage<List<ProjectManager>> getProjectManagerByProjectContainsIgnoreCase(String projectName) {
        return CompletableFuture.supplyAsync(() -> projectManagerRepository.getProjectManagerByProjectContainsIgnoreCase(projectName), executor);
    }
}
