package the.bug.tech.brasch_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.model.ProjectManager;
import the.bug.tech.brasch_management_system.repository.ProjectManagerRepositoryAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {

    private final ProjectManagerRepositoryAsync projectManagerRepositoryAsync;

    @Autowired
    public ProjectManagerServiceImpl(ProjectManagerRepositoryAsync projectManagerRepositoryAsync) {
        this.projectManagerRepositoryAsync = projectManagerRepositoryAsync;
    }

    @Override
    @Transactional
    public CompletionStage<ProjectManager> insertProjectManager(ProjectManager projectManager) {
        return projectManagerRepositoryAsync.insertProjectManager(projectManager);
    }

    @Override
    public CompletionStage<ProjectManager> getProjectManagerById(String projectManagerId) {
        return projectManagerRepositoryAsync.getProjectManagerById(projectManagerId);
    }

    @Override
    public CompletionStage<List<ProjectManager>> getAllProjectManager() {
        return projectManagerRepositoryAsync.getAllProjectManagers();
    }

    @Override
    @Transactional
    public CompletionStage<ProjectManager> updateProjectManager(ProjectManager projectManager) {
        return projectManagerRepositoryAsync.updateProjectManager(projectManager);
    }

    @Override
    @Transactional
    public CompletionStage<Void> deleteProjectManager(ProjectManager projectManager) {
        return projectManagerRepositoryAsync.deleteProjectManager(projectManager);
    }

    @Override
    public CompletionStage<ProjectManager> getProjectManagerByNameContainsIgnoreCase(String projectManagerName) {
        return projectManagerRepositoryAsync.getProjectManagerByNameContainsIgnoreCase(projectManagerName);
    }

    @Override
    public CompletionStage<ProjectManager> getProjectManagerByProjectContainsIgnoreCase(String projectName) {
        return projectManagerRepositoryAsync.getProjectManagerByProjectContainsIgnoreCase(projectName);
    }
}
