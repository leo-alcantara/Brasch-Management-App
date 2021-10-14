package the.bug.tech.brasch_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.data.repository.ProjectManagerRepository;
import the.bug.tech.brasch_management_system.model.ProjectManager;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectManagerService {

    private final ProjectManagerRepository projectManagerRepository;

    @Autowired
    public ProjectManagerService(ProjectManagerRepository projectManagerRepository) {
        this.projectManagerRepository = projectManagerRepository;
    }

    public ProjectManager createProjectManager(ProjectManager projectManager) {
        return projectManagerRepository.save(projectManager);
    }

    public ProjectManager findById(String projectManagerId) {
        Optional<ProjectManager> foundById = projectManagerRepository.findById(projectManagerId);
        return foundById.orElseThrow(IllegalArgumentException::new);
    }

    public List<ProjectManager> findAll() {
        return projectManagerRepository.findAll();
    }

    @Transactional
    public ProjectManager update(ProjectManager projectManager) {
        ProjectManager original = projectManagerRepository.findById(projectManager.getProjectManagerId()).get();
        original.setProjectManagerPerson(projectManager.getProjectManagerPerson());
        original.setProjectList(projectManager.getProjectList());
        return original;
    }

    public void delete(String projectManagerId) {
        projectManagerRepository.deleteById(projectManagerId);
    }

    public ProjectManager findProjectManagerByNameContainsIgnoreCase(String projectManagerName) {
        return projectManagerRepository.findProjectManagerByNameContainsIgnoreCase(projectManagerName);
    }

    public ProjectManager findProjectManagerByProjectContainsIgnoreCase(String projectName) {
        return projectManagerRepository.findProjectManagerByProjectContainsIgnoreCase(projectName);
    }


}
