package the.bug.tech.brasch_management_system.service;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.repository.ProjectManagerRepository;
import the.bug.tech.brasch_management_system.model.ProjectManager;
import the.bug.tech.brasch_management_system.repository.ProjectManagerRepositoryAsync;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@Service
public class ProjectManagerService {

    private final ProjectManagerRepository projectManagerRepository;
    private final ProjectManagerRepositoryAsync projectManagerRepositoryAsync;

    @Autowired
    public ProjectManagerService(ProjectManagerRepository projectManagerRepository, ProjectManagerRepositoryAsync projectManagerRepositoryAsync) {
        this.projectManagerRepository = projectManagerRepository;
        this.projectManagerRepositoryAsync = projectManagerRepositoryAsync;
    }

    @Async
    public ProjectManager createProjectManager(ProjectManager projectManager) {
        return projectManagerRepository.save(projectManager);
    }

    @Async
    public ProjectManager findById(String projectManagerId) {
        Optional<ProjectManager> foundById = projectManagerRepository.findById(projectManagerId);
        return foundById.orElseThrow(IllegalArgumentException::new);
    }

    @Async
    public List<ProjectManager> findAll() {
        return projectManagerRepository.findAll();
    }

    @Async
    @Transactional
    public ProjectManager update(ProjectManager projectManager) {
        ProjectManager original = projectManagerRepository.findById(projectManager.getProjectManagerId()).get();
        original.setProjectManagerPerson(projectManager.getProjectManagerPerson());
        original.setProjectList(projectManager.getProjectList());
        return original;
    }

    @Async
    public void delete(String projectManagerId) {
        projectManagerRepository.deleteById(projectManagerId);
    }

    public CompletionStage<Option<ProjectManager>> findProjectManagerByNameContainsIgnoreCase(String projectManagerName) {
        return projectManagerRepositoryAsync.getProjectManagerByNameContainsIgnoreCase(projectManagerName);
    }

    public CompletionStage<Option<ProjectManager>> findProjectManagerByProjectContainsIgnoreCase(String projectName) {
        return projectManagerRepositoryAsync.getProjectManagerByProjectContainsIgnoreCase(projectName);
    }


}
