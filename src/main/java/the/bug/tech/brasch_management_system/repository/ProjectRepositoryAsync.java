package the.bug.tech.brasch_management_system.repository;

import io.vavr.control.Option;
import org.springframework.stereotype.Repository;
import the.bug.tech.brasch_management_system.model.Project;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

@Repository
public class ProjectRepositoryAsync {

    private final ProjectRepository PROJECT_REPOSITORY;
    private final Executor EXECUTOR;
    @PersistenceContext
    private final EntityManager ENTITY_MANAGER;

    public ProjectRepositoryAsync(ProjectRepository PROJECT_REPOSITORY, Executor EXECUTOR, EntityManager ENTITY_MANAGER) {
        this.PROJECT_REPOSITORY = PROJECT_REPOSITORY;
        this.EXECUTOR = EXECUTOR;
        this.ENTITY_MANAGER = ENTITY_MANAGER;
    }

    public CompletionStage<Project> insertProject(Project project) {
        return CompletableFuture.supplyAsync(() -> PROJECT_REPOSITORY.save(project), EXECUTOR);
    }

    public CompletionStage<Project> getProjectById(String projectId) {
        return CompletableFuture.supplyAsync(() -> PROJECT_REPOSITORY.getById(projectId), EXECUTOR);
    }

    public CompletionStage<List<Project>> getAllProjects() {
        return CompletableFuture.supplyAsync(() -> PROJECT_REPOSITORY.findAll(), EXECUTOR);
    }

    public CompletionStage<Option<Void>> deleteProject(Project project) {
        return CompletableFuture.supplyAsync(() -> {
            PROJECT_REPOSITORY.delete(project);
            return null;
        }, EXECUTOR);
    }

    public CompletionStage<Project> updateProject(Project project) {
        return CompletableFuture.supplyAsync(() -> ENTITY_MANAGER.merge(project), EXECUTOR);
    }

    public CompletionStage<Project> getProjectByNameContainsIgnoreCase(String projectName) {
        return CompletableFuture.supplyAsync(() -> PROJECT_REPOSITORY.getProjectByNameContainsIgnoreCase(projectName), EXECUTOR);
    }

    public CompletionStage<Project> getProjectByAddressContainsIgnoreCase(String projectAddress) {
        return CompletableFuture.supplyAsync(() -> PROJECT_REPOSITORY.getProjectByAddressContainsIgnoreCase(projectAddress), EXECUTOR);
    }

    public CompletionStage<List<Project>> getProjectByCompanyContainsIgnoreCase(String companyName) {
        return CompletableFuture.supplyAsync(() -> PROJECT_REPOSITORY.getProjectByCompanyContainsIgnoreCase(companyName), EXECUTOR);
    }

    public CompletionStage<List<Project>> getProjectByProjectManagerContainsIgnoreCase(String projectManagerName) {
        return CompletableFuture.supplyAsync(() -> PROJECT_REPOSITORY.getProjectByProjectManagerContainsIgnoreCase(projectManagerName), EXECUTOR);
    }

    public CompletionStage<List<Project>> getProjectByContactPersonContainsIgnoreCase(String contactPersonName) {
        return CompletableFuture.supplyAsync(() -> PROJECT_REPOSITORY.getProjectByContactPersonContainsIgnoreCase(contactPersonName), EXECUTOR);
    }
}
