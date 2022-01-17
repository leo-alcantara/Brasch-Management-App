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

    private final ProjectManagerRepositoryAsync PROJECT_MANAGER_REPOSITORY_ASYNC;

    @Autowired
    public ProjectManagerServiceImpl(ProjectManagerRepositoryAsync PROJECT_MANAGER_REPOSITORY_ASYNC) {
        this.PROJECT_MANAGER_REPOSITORY_ASYNC = PROJECT_MANAGER_REPOSITORY_ASYNC;
    }

    @Override
    @Transactional
    public CompletionStage<ProjectManager> insertProjectManager(ProjectManager projectManager) {
        return PROJECT_MANAGER_REPOSITORY_ASYNC.insertProjectManager(projectManager);
    }

    @Override
    public CompletionStage<ProjectManager> getProjectManagerById(String projectManagerId) {
        return PROJECT_MANAGER_REPOSITORY_ASYNC.getProjectManagerById(projectManagerId);
    }

    @Override
    public CompletionStage<List<ProjectManager>> getAllProjectManager() {
        return PROJECT_MANAGER_REPOSITORY_ASYNC.getAllProjectManagers();
    }

    @Override
    @Transactional
    public CompletionStage<ProjectManager> updateProjectManager(ProjectManager projectManager) {
        return PROJECT_MANAGER_REPOSITORY_ASYNC.updateProjectManager(projectManager);
    }

    @Override
    @Transactional
    public CompletionStage<Void> deleteProjectManager(ProjectManager projectManager) {
        return PROJECT_MANAGER_REPOSITORY_ASYNC.deleteProjectManager(projectManager);
    }

    @Override
    public CompletionStage<ProjectManager> getProjectManagerByNameContainsIgnoreCase(String projectManagerName) {
        return PROJECT_MANAGER_REPOSITORY_ASYNC.getProjectManagerByNameContainsIgnoreCase(projectManagerName);
    }

    @Override
    public CompletionStage<ProjectManager> getProjectManagerByProjectContainsIgnoreCase(String projectName) {
        return PROJECT_MANAGER_REPOSITORY_ASYNC.getProjectManagerByProjectContainsIgnoreCase(projectName);
    }
}
