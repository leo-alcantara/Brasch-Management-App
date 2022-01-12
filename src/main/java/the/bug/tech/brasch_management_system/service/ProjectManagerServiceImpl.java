package the.bug.tech.brasch_management_system.service;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.repository.ProjectManagerRepository;
import the.bug.tech.brasch_management_system.model.ProjectManager;
import the.bug.tech.brasch_management_system.repository.ProjectManagerRepositoryAsync;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {

    private final ProjectManagerRepository projectManagerRepository;
    private final ProjectManagerRepositoryAsync projectManagerRepositoryAsync;

    @Autowired
    public ProjectManagerServiceImpl(ProjectManagerRepository projectManagerRepository, ProjectManagerRepositoryAsync projectManagerRepositoryAsync) {
        this.projectManagerRepository = projectManagerRepository;
        this.projectManagerRepositoryAsync = projectManagerRepositoryAsync;
    }

    @Override
    @Transactional
    public ProjectManager insertProjectManager(ProjectManager projectManager) {
        return projectManagerRepository.save(projectManager);
    }


    @Override
    public ProjectManager getProjectManagerById(String projectManagerId) {
        Optional<ProjectManager> foundById = projectManagerRepository.findById(projectManagerId);
        return foundById.orElseThrow(IllegalArgumentException::new);
    }


    @Override
    public List<ProjectManager> getAllProjectManager() {
        return projectManagerRepository.findAll();
    }

    @Override
    @Transactional
    public ProjectManager updateProjectManager(ProjectManager projectManager) {
        ProjectManager original = projectManagerRepository.findById(projectManager.getProjectManagerId()).get();
        original.setProjectManagerPerson(projectManager.getProjectManagerPerson());
        original.setProjectList(projectManager.getProjectList());
        return original;
    }

    @Override
    @Transactional
    public void deleteProjectManager(String projectManagerId) {
        projectManagerRepository.deleteById(projectManagerId);
    }

    @Override
    public CompletionStage<Option<ProjectManager>> getProjectManagerByNameContainsIgnoreCase(String projectManagerName) {
        return projectManagerRepositoryAsync.getProjectManagerByNameContainsIgnoreCase(projectManagerName);
    }

    @Override
    public CompletionStage<Option<ProjectManager>> getProjectManagerByProjectContainsIgnoreCase(String projectName) {
        return projectManagerRepositoryAsync.getProjectManagerByProjectContainsIgnoreCase(projectName);
    }


}
