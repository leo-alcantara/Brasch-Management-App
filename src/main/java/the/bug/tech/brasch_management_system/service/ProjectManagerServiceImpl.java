package the.bug.tech.brasch_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.exceptions.ResourceNotFoundException;
import the.bug.tech.brasch_management_system.model.ProjectManager;
import the.bug.tech.brasch_management_system.repository.ProjectManagerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {

    private final ProjectManagerRepository projectManagerRepository;

    @Autowired
    public ProjectManagerServiceImpl(ProjectManagerRepository projectManagerRepository) {
        this.projectManagerRepository = projectManagerRepository;
    }

    @Transactional
    public ProjectManager insertProjectManager(ProjectManager projectManager) {
        return projectManagerRepository.save(projectManager);
    }

    @Transactional
    public ProjectManager getProjectManagerById(Integer projectManagerId) {
        Optional<ProjectManager> foundProjectManager = projectManagerRepository.findById(projectManagerId);

        if (foundProjectManager.isPresent()) {
            return foundProjectManager.get();
        } else {
            throw new ResourceNotFoundException("Could not find Project Manager with id " + projectManagerId);
        }
    }

    @Transactional
    public List<ProjectManager> getAllProjectManager() {
        return projectManagerRepository.findAll();
    }

    @Transactional
    public ProjectManager updateProjectManager(Integer projectManagerId, ProjectManager projectManager) {

        Optional<ProjectManager> original = projectManagerRepository.findById(projectManagerId);

        if (original.isPresent()) {
            original.get().setProjectManagerPerson(projectManager.getProjectManagerPerson());
            original.get().setProjectList(projectManager.getProjectList());
            return original.get();
        } else {
            throw new ResourceNotFoundException("Could not update Project Manager with id " + projectManagerId);
        }
    }

    @Transactional
    public void deleteProjectManager(Integer projectManagerId) {
        projectManagerRepository.deleteById(projectManagerId);
    }

    @Override
    public List<ProjectManager> getProjectManagerByNameContainsIgnoreCase(String projectManagerName) {
        return projectManagerRepository.getProjectManagerByNameContainsIgnoreCase(projectManagerName);
    }

    @Override
    public List<ProjectManager> getProjectManagerByProjectContainsIgnoreCase(String projectName) {
        return projectManagerRepository.getProjectManagerByProjectContainsIgnoreCase(projectName);
    }
}
