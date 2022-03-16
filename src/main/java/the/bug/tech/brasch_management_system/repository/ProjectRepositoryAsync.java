package the.bug.tech.brasch_management_system.repository;

import org.springframework.stereotype.Repository;
import the.bug.tech.brasch_management_system.model.Project;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

@Repository
public class ProjectRepositoryAsync {

    private final ProjectRepository projectRepository;
    private final Executor executor;

    public ProjectRepositoryAsync(ProjectRepository projectRepository, Executor executor) {
        this.projectRepository = projectRepository;
        this.executor = executor;
    }

    public CompletionStage<Project> insertProject(Project project) {
        return CompletableFuture.supplyAsync(() -> projectRepository.create(project), executor);
    }

    public CompletionStage<Project> getProjectById(Integer projectId) {
        return CompletableFuture.supplyAsync(() -> projectRepository.findById(projectId), executor);
    }

    public CompletionStage<List<Project>> getAllProjects() {
        return CompletableFuture.supplyAsync(() -> projectRepository.findAll(), executor);
    }

    public CompletionStage<Void> deleteProject(Project project) {
        return CompletableFuture.runAsync(() ->
                projectRepository.delete(project), executor);
    }

    public CompletionStage<Project> updateProject(Project project) {
        return CompletableFuture.supplyAsync(() -> projectRepository.update(project), executor);
    }

    public CompletionStage<List<Project>> getProjectByNameContainsIgnoreCase(String projectName) {
        return CompletableFuture.supplyAsync(() -> projectRepository.getProjectByNameContainsIgnoreCase(projectName), executor);
    }

    public CompletionStage<List<Project>> getProjectByAddressContainsIgnoreCase(String projectAddress) {
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
