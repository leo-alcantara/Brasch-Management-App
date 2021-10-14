package the.bug.tech.brasch_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.data.repository.ProjectRepository;
import the.bug.tech.brasch_management_system.model.Project;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project){
       return projectRepository.save(project);
    }

    public Project findById(String projectId){
        Optional<Project> foundById= projectRepository.findById(projectId);
        return foundById.orElseThrow(IllegalArgumentException::new);
    }

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    @Transactional
    public Project update(Project project){
        Project original= projectRepository.findById(project.getProjectId()).get();
        original.setProjectName(project.getProjectName());
        original.setCompany(project.getCompany());
        original.setProjectDescription(project.getProjectDescription());
        original.setProjectLocal(project.getProjectLocal());
        original.setProjectedStartDate(project.getProjectedStartDate());
        original.setProjectedConclusionDate(project.getProjectedConclusionDate());
        original.setContactPerson(project.getContactPerson());
        original.setProjectManager(project.getProjectManager());
        original.setProjectStatus(project.getProjectStatus());

        return original;
    }

    public void delete(String projectId){
        projectRepository.deleteById(projectId);
    }

    public Project findProjectByNameContainsIgnoreCase(String projectName){
        return projectRepository.findProjectByNameContainsIgnoreCase(projectName);
    }

    public Project findProjectByAddressContainsIgnoreCase(String projectAddress){
        return projectRepository.findProjectByAddressContainsIgnoreCase(projectAddress);
    }

    public List<Project> findProjectByCompanyContainsIgnoreCase(String companyName){
        return projectRepository.findProjectByCompanyContainsIgnoreCase(companyName);
    }

    public List<Project> findProjectByProjectManagerContainsIgnoreCase(String name){
        return projectRepository.findProjectByProjectManagerContainsIgnoreCase(name);
    }

    public List<Project> findProjectByContactPersonContainsIgnoreCase(String name){
        return projectRepository.findProjectByContactPersonContainsIgnoreCase(name);
    }









}
