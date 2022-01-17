package the.bug.tech.brasch_management_system.service;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.model.Project;
import the.bug.tech.brasch_management_system.repository.ProjectRepositoryAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepositoryAsync PROJECT_REPOSITORY_ASYNC;

    @Autowired
    public ProjectServiceImpl(ProjectRepositoryAsync PROJECT_REPOSITORY_ASYNC) {
        this.PROJECT_REPOSITORY_ASYNC = PROJECT_REPOSITORY_ASYNC;
    }

    @Override
    @Transactional
    public CompletionStage<Project> insertProject(Project project) {
        return PROJECT_REPOSITORY_ASYNC.insertProject(project);
    }

    @Override
    public CompletionStage<Project> getProjectById(String projectId) {
        return PROJECT_REPOSITORY_ASYNC.getProjectById(projectId);
    }

    @Override
    public CompletionStage<List<Project>> getAllProject() {
        return PROJECT_REPOSITORY_ASYNC.getAllProjects();
    }

    @Override
    @Transactional
    public CompletionStage<Project> updateProject(Project project) {
        return PROJECT_REPOSITORY_ASYNC.updateProject(project);
    }

    @Override
    @Transactional
    public CompletionStage<Option<Void>> deleteProject(Project project) {
        return PROJECT_REPOSITORY_ASYNC.deleteProject(project);
    }

    @Override
    public CompletionStage<Project> getProjectByNameContainsIgnoreCase(String projectName) {
        return PROJECT_REPOSITORY_ASYNC.getProjectByNameContainsIgnoreCase(projectName);
    }

    @Override
    public CompletionStage<Project> getProjectByAddressContainsIgnoreCase(String projectAddress) {
        return PROJECT_REPOSITORY_ASYNC.getProjectByAddressContainsIgnoreCase(projectAddress);
    }

    @Override
    public CompletionStage<List<Project>> getProjectByCompanyContainsIgnoreCase(String companyName) {
        return PROJECT_REPOSITORY_ASYNC.getProjectByCompanyContainsIgnoreCase(companyName);
    }

    @Override
    public CompletionStage<List<Project>> getProjectByProjectManagerContainsIgnoreCase(String projectManagerName) {
        return PROJECT_REPOSITORY_ASYNC.getProjectByProjectManagerContainsIgnoreCase(projectManagerName);
    }

    @Override
    public CompletionStage<List<Project>> getProjectByContactPersonContainsIgnoreCase(String contactPersonName) {
        return PROJECT_REPOSITORY_ASYNC.getProjectByContactPersonContainsIgnoreCase(contactPersonName);
    }
}
