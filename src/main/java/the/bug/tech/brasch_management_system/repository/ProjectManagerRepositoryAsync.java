package the.bug.tech.brasch_management_system.repository;

import io.vavr.control.Option;
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
        return CompletableFuture.supplyAsync(() -> projectManagerRepository.save(projectManager), executor);
    }

    public CompletionStage<ProjectManager> getProjectManagerById(String projectManagerId) {
        return CompletableFuture.supplyAsync(() -> projectManagerRepository.getById(projectManagerId), executor);
    }

    public CompletionStage<List<ProjectManager>> getAllProjectManagers() {
        return CompletableFuture.supplyAsync(() -> projectManagerRepository.findAll(), executor);
    }

    public CompletionStage<Void> deleteProjectManager(ProjectManager projectManager) {
        return CompletableFuture.supplyAsync(() -> {
            projectManagerRepository.delete(projectManager);
            return null;
        }, executor);
    }

    public CompletionStage<ProjectManager> updateProjectManager(ProjectManager projectManager) {
        return CompletableFuture.supplyAsync(() -> entityManager.merge(projectManager), executor);
    }

    public CompletionStage<ProjectManager> getProjectManagerByNameContainsIgnoreCase(String projectManagerName) {
        return CompletableFuture.supplyAsync(() -> projectManagerRepository.getProjectManagerByNameContainsIgnoreCase(projectManagerName), executor);
    }

    public CompletionStage<ProjectManager> getProjectManagerByProjectContainsIgnoreCase(String projectName) {
        return CompletableFuture.supplyAsync(() -> projectManagerRepository.getProjectManagerByProjectContainsIgnoreCase(projectName), executor);
    }
}
