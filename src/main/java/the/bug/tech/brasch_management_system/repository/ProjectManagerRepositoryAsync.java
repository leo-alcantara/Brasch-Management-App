package the.bug.tech.brasch_management_system.repository;

import io.vavr.control.Option;
import org.springframework.stereotype.Repository;
import the.bug.tech.brasch_management_system.model.ProjectManager;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

@Repository
public class ProjectManagerRepositoryAsync {

    private final ProjectManagerRepository projectManagerRepository;
    private final Executor executor;

    public ProjectManagerRepositoryAsync(ProjectManagerRepository projectManagerRepository, Executor executor) {
        this.projectManagerRepository = projectManagerRepository;
        this.executor = executor;
    }

    public CompletionStage<Option<ProjectManager>> getProjectManagerByNameContainsIgnoreCase (String projectManagerName) {
        return CompletableFuture.supplyAsync(() -> projectManagerRepository.getProjectManagerByNameContainsIgnoreCase(projectManagerName), executor);
    }

    public CompletionStage<Option<ProjectManager>> getProjectManagerByProjectContainsIgnoreCase (String projectName) {
        return CompletableFuture.supplyAsync(() -> projectManagerRepository.getProjectManagerByProjectContainsIgnoreCase(projectName), executor);
    }
}
