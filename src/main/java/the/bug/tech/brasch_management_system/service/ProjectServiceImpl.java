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

    private final ProjectRepositoryAsync projectRepositoryAsync;

    @Autowired
    public ProjectServiceImpl(ProjectRepositoryAsync projectRepositoryAsync) {
        this.projectRepositoryAsync = projectRepositoryAsync;
    }

    @Override
    @Transactional
    public CompletionStage<Project> insertProject(Project project) {
        return projectRepositoryAsync.insertProject(project);
    }

    @Override
    public CompletionStage<Project> getProjectById(String projectId) {
        return projectRepositoryAsync.getProjectById(projectId);
    }

    @Override
    public CompletionStage<List<Project>> getAllProject() {
        return projectRepositoryAsync.getAllProjects();
    }

    @Override
    @Transactional
    public CompletionStage<Project> updateProject(Project project) {
        return projectRepositoryAsync.updateProject(project);
    }

    @Override
    @Transactional
    public CompletionStage<Option<Void>> deleteProject(Project project) {
        return projectRepositoryAsync.deleteProject(project);
    }

    @Override
    public CompletionStage<Project> getProjectByNameContainsIgnoreCase(String projectName) {
        return projectRepositoryAsync.getProjectByNameContainsIgnoreCase(projectName);
    }

    @Override
    public CompletionStage<Project> getProjectByAddressContainsIgnoreCase(String projectAddress) {
        return projectRepositoryAsync.getProjectByAddressContainsIgnoreCase(projectAddress);
    }

    @Override
    public CompletionStage<List<Project>> getProjectByCompanyContainsIgnoreCase(String companyName) {
        return projectRepositoryAsync.getProjectByCompanyContainsIgnoreCase(companyName);
    }

    @Override
    public CompletionStage<List<Project>> getProjectByProjectManagerContainsIgnoreCase(String projectManagerName) {
        return projectRepositoryAsync.getProjectByProjectManagerContainsIgnoreCase(projectManagerName);
    }

    @Override
    public CompletionStage<List<Project>> getProjectByContactPersonContainsIgnoreCase(String contactPersonName) {
        return projectRepositoryAsync.getProjectByContactPersonContainsIgnoreCase(contactPersonName);
    }
}
