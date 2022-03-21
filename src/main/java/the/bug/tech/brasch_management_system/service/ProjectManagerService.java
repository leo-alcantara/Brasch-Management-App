package the.bug.tech.brasch_management_system.service;

import the.bug.tech.brasch_management_system.model.ProjectManager;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface ProjectManagerService {

    List<ProjectManager> getProjectManagerByNameContainsIgnoreCase(String projectManagerName);

    List<ProjectManager> getProjectManagerByProjectContainsIgnoreCase(String projectName);
}
