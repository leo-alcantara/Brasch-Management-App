package the.bug.tech.brasch_management_system.service;

import io.vavr.control.Option;
import the.bug.tech.brasch_management_system.model.ProjectManager;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface ProjectManagerService {

    CompletionStage<ProjectManager> insertProjectManager(ProjectManager projectManager);

    CompletionStage<ProjectManager> getProjectManagerById(String projectManagerId);

    CompletionStage<List<ProjectManager>> getAllProjectManager();

    CompletionStage<ProjectManager> updateProjectManager(ProjectManager projectManager);

    CompletionStage<Option<Void>> deleteProjectManager(ProjectManager projectManager);

    CompletionStage<Option<ProjectManager>> getProjectManagerByNameContainsIgnoreCase(String projectManagerName);

    CompletionStage<Option<ProjectManager>> getProjectManagerByProjectContainsIgnoreCase(String projectName);
}
