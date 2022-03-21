package the.bug.tech.brasch_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.exceptions.ResourceNotFoundException;
import the.bug.tech.brasch_management_system.model.Project;
import the.bug.tech.brasch_management_system.repository.ProjectRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Transactional
    public Project insertProject(Project project) {
        projectRepository.save(project);
        return project;
    }

    @Transactional
    public Project getProjectById(Integer projectId) {
        Optional<Project> foundProject = projectRepository.findById(projectId);

        if (foundProject.isPresent()) {
            return foundProject.get();
        } else {
            throw new ResourceNotFoundException("Could not find Project with id " + projectId);
        }
    }

    @Transactional
    public List<Project> getAllProject() {
        return projectRepository.findAll();
    }

    @Transactional
    public Project updateProject(Integer projectId, Project project) {

        Optional<Project> original = projectRepository.findById(projectId);

        if (original.isPresent()) {
            original.get().setProjectName(project.getProjectName());
            original.get().setProjectDescription(project.getProjectDescription());
            original.get().setProjectLocal(project.getProjectLocal());
            original.get().setProjectStatus(project.getProjectStatus());
            original.get().setProjectedStartDate(project.getProjectedStartDate());
            original.get().setProjectedConclusionDate(project.getProjectedConclusionDate());
            original.get().setCompany(project.getCompany());
            original.get().setProjectManager(project.getProjectManager());
            original.get().setContactPersonList(project.getContactPersonList());
            return original.get();
        } else {
            throw new ResourceNotFoundException("Could not update Project with id " + projectId);
        }
    }

    @Transactional
    public void deleteProject(Integer projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    @Transactional
    public List<Project> getProjectByNameContainsIgnoreCase(String projectName) {
        return projectRepository.getProjectByNameContainsIgnoreCase(projectName);
    }

    @Override
    @Transactional
    public List<Project> getProjectByAddressContainsIgnoreCase(String projectAddress) {
        return projectRepository.getProjectByAddressContainsIgnoreCase(projectAddress);
    }

    @Override
    @Transactional
    public List<Project> getProjectByCompanyContainsIgnoreCase(String companyName) {
        return projectRepository.getProjectByCompanyContainsIgnoreCase(companyName);
    }

    @Override
    @Transactional
    public List<Project> getProjectByProjectManagerContainsIgnoreCase(String projectManagerName) {
        return projectRepository.getProjectByProjectManagerContainsIgnoreCase(projectManagerName);
    }

    @Override
    @Transactional
    public List<Project> getProjectByContactPersonContainsIgnoreCase(String contactPersonName) {
        return projectRepository.getProjectByContactPersonContainsIgnoreCase(contactPersonName);
    }
}
