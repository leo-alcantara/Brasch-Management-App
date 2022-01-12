package the.bug.tech.brasch_management_system.service;

import io.vavr.control.Option;
import the.bug.tech.brasch_management_system.model.Project;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface ProjectService {

    CompletionStage<Project> insertProject(Project project);

    CompletionStage<Project> getProjectById(String projectId);

    CompletionStage<List<Project>> getAllProject();

    CompletionStage<Project> updateProject(Project project);

    CompletionStage<Option<Void>> deleteProject(Project project);

    CompletionStage<Option<Project>> getProjectByNameContainsIgnoreCase(String projectName);

    CompletionStage<Option<Project>> getProjectByAddressContainsIgnoreCase(String projectAddress);

    CompletionStage<List<Project>> getProjectByCompanyContainsIgnoreCase(String companyName);

    CompletionStage<List<Project>> getProjectByProjectManagerContainsIgnoreCase(String projectManagerName);

    CompletionStage<List<Project>> getProjectByContactPersonContainsIgnoreCase(String contactPersonName);
}
