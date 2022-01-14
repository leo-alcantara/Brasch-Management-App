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

    private final ProjectRepository projectRepository;
    private final Executor executor;
    @PersistenceContext
    private final EntityManager entityManager;

    public ProjectRepositoryAsync(ProjectRepository projectRepository, Executor executor, EntityManager entityManager) {
        this.projectRepository = projectRepository;
        this.executor = executor;
        this.entityManager = entityManager;
    }

    public CompletionStage<Project> insertProject(Project project) {
        return CompletableFuture.supplyAsync(() -> projectRepository.save(project), executor);
    }

    public CompletionStage<Project> getProjectById(String projectId) {
        return CompletableFuture.supplyAsync(() -> projectRepository.getById(projectId), executor);
    }

    public CompletionStage<List<Project>> getAllProjects() {
        return CompletableFuture.supplyAsync(() -> projectRepository.findAll(), executor);
    }

    public CompletionStage<Option<Void>> deleteProject(Project project) {
        return CompletableFuture.supplyAsync(() -> {
            projectRepository.delete(project);
            return null;
        }, executor);
    }

    public CompletionStage<Project> updateProject(Project project) {
        return CompletableFuture.supplyAsync(() -> entityManager.merge(project), executor);
    }

    public CompletionStage<Project> getProjectByNameContainsIgnoreCase(String projectName) {
        return CompletableFuture.supplyAsync(() -> projectRepository.getProjectByNameContainsIgnoreCase(projectName), executor);
    }

    public CompletionStage<Project> getProjectByAddressContainsIgnoreCase(String projectAddress) {
        return CompletableFuture.supplyAsync(() -> projectRepository.getProjectByAddressContainsIgnoreCase(projectAddress), executor);
    }

    public CompletionStage<List<Project>> getProjectByCompanyContainsIgnoreCase(String companyName) {
        return CompletableFuture.supplyAsync(() -> projectRepository.getProjectByCompanyContainsIgnoreCase(companyName), executor);
    }

    public CompletionStage<List<Project>> getProjectByProjectManagerContainsIgnoreCase(String projectManagerName) {
        return CompletableFuture.supplyAsync(() -> projectRepository.getProjectByProjectManagerContainsIgnoreCase(projectManagerName), executor);
    }

    public CompletionStage<List<Project>> getProjectByContactPersonContainsIgnoreCase(String contactPersonName) {
        return CompletableFuture.supplyAsync(() -> projectRepository.getProjectByContactPersonContainsIgnoreCase(contactPersonName), executor);
    }
}
