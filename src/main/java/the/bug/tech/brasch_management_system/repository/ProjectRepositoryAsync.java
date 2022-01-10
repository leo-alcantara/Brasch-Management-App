package the.bug.tech.brasch_management_system.repository;

import io.vavr.control.Option;
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

    public CompletionStage<Option<Project>> getProjectByNameContainsIgnoreCase (String projectName) {
        return CompletableFuture.supplyAsync(() -> projectRepository.getProjectByNameContainsIgnoreCase(projectName), executor);
    }

    public CompletionStage<Option<Project>> getProjectByAddressContainsIgnoreCase (String projectAddress) {
        return CompletableFuture.supplyAsync(() -> projectRepository.getProjectByAddressContainsIgnoreCase(projectAddress), executor);
    }

    public CompletionStage<List<Project>> getProjectByCompanyContainsIgnoreCase (String companyName) {
        return CompletableFuture.supplyAsync(() -> projectRepository.getProjectByCompanyContainsIgnoreCase(companyName), executor);
    }

    public CompletionStage<List<Project>> getProjectByProjectManagerContainsIgnoreCase (String projectManagerName) {
        return CompletableFuture.supplyAsync(() -> projectRepository.getProjectByProjectManagerContainsIgnoreCase(projectManagerName), executor);
    }

    public CompletionStage<List<Project>> getProjectByContactPersonContainsIgnoreCase (String contactPersonName) {
        return CompletableFuture.supplyAsync(() -> projectRepository.getProjectByContactPersonContainsIgnoreCase(contactPersonName), executor);
    }

}
