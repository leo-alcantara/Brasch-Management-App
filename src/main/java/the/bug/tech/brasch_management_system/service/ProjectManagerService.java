package the.bug.tech.brasch_management_system.service;

import io.vavr.control.Option;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.model.ProjectManager;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface ProjectManagerService {
    @Transactional
    ProjectManager insertProjectManager(ProjectManager projectManager);

    ProjectManager getProjectManagerById(String projectManagerId);

    List<ProjectManager> getAllProjectManager();

    @Transactional
    ProjectManager updateProjectManager(ProjectManager projectManager);

    @Transactional
    void deleteProjectManager(String projectManagerId);

    CompletionStage<Option<ProjectManager>> getProjectManagerByNameContainsIgnoreCase(String projectManagerName);

    CompletionStage<Option<ProjectManager>> getProjectManagerByProjectContainsIgnoreCase(String projectName);
}
