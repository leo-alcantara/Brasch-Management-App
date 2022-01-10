package the.bug.tech.brasch_management_system.service;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.repository.ProjectRepository;
import the.bug.tech.brasch_management_system.model.Project;
import the.bug.tech.brasch_management_system.repository.ProjectRepositoryAsync;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectRepositoryAsync projectRepositoryAsync;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectRepositoryAsync projectRepositoryAsync) {
        this.projectRepository = projectRepository;
        this.projectRepositoryAsync = projectRepositoryAsync;
    }

    @Async
    public Project createProject(Project project){
       return projectRepository.save(project);
    }

    @Async
    public Project findById(String projectId){
        Optional<Project> foundById= projectRepository.findById(projectId);
        return foundById.orElseThrow(IllegalArgumentException::new);
    }

    @Async
    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    @Transactional
    @Async
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

    @Async
    public void delete(String projectId){
        projectRepository.deleteById(projectId);
    }

    public CompletionStage<Option<Project>> getProjectByNameContainsIgnoreCase(String projectName){
        return projectRepositoryAsync.getProjectByNameContainsIgnoreCase(projectName);
    }

    public CompletionStage<Option<Project>> getProjectByAddressContainsIgnoreCase(String projectAddress){
        return projectRepositoryAsync.getProjectByAddressContainsIgnoreCase(projectAddress);
    }

    public CompletionStage<List<Project>> getProjectByCompanyContainsIgnoreCase(String companyName){
        return projectRepositoryAsync.getProjectByCompanyContainsIgnoreCase(companyName);
    }

    public CompletionStage<List<Project>> getProjectByProjectManagerContainsIgnoreCase(String projectManagerName){
        return projectRepositoryAsync.getProjectByProjectManagerContainsIgnoreCase(projectManagerName);
    }

    public CompletionStage<List<Project>> getProjectByContactPersonContainsIgnoreCase(String contactPersonName){
        return projectRepositoryAsync.getProjectByContactPersonContainsIgnoreCase(contactPersonName);
    }









}
