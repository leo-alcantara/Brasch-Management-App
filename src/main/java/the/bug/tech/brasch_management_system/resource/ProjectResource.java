package the.bug.tech.brasch_management_system.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.model.Project;
import the.bug.tech.brasch_management_system.model.dto.ProjectDto;
import the.bug.tech.brasch_management_system.service.EntityDtoMapper;
import the.bug.tech.brasch_management_system.service.ProjectServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/project")
public class ProjectResource {

    private final ProjectServiceImpl projectServiceImpl;
    private final EntityDtoMapper mapper;

    @Autowired
    public ProjectResource(ProjectServiceImpl projectServiceImpl, EntityDtoMapper mapper) {
        this.projectServiceImpl = projectServiceImpl;
        this.mapper = mapper;
    }

    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ProjectDto> insertProject(@RequestBody ProjectDto projectdto) {
        Project insertedProject = projectServiceImpl.insertProject(mapper.toProject(projectdto));
        ProjectDto insertedProjectDto = mapper.toProjectDto(insertedProject);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedProjectDto);
    }

    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<ProjectDto> getProjectById(@RequestParam Integer projectId) {
        Project projectFoundById = projectServiceImpl.getProjectById(projectId);
        ProjectDto projectFoundByIdDto = mapper.toProjectDto(projectFoundById);
        return ResponseEntity.ok(projectFoundByIdDto);
    }

    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<Project> projects = projectServiceImpl.getAllProject();
        List<ProjectDto> projectsDto = projects
                .stream()
                .map(mapper::toProjectDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsDto);
    }

    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ProjectDto> updateProject(Integer projectToUpdateId,
                                                    @RequestBody ProjectDto projectDto) {
        Project convertedProject = projectServiceImpl.updateProject(projectToUpdateId, mapper.toProject(projectDto));
        ProjectDto convertedProjectDto = mapper.toProjectDto(convertedProject);
        return ResponseEntity.ok().body(convertedProjectDto);
    }

    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProject(@RequestBody Integer projectId) {
        projectServiceImpl.deleteProject(projectId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(produces = "application/json", value = "/getByName", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectDto>> getProjectByNameContainsIgnoreCase(@RequestParam String projectName) {
        List<Project> projectsFoundByName = projectServiceImpl.getProjectByNameContainsIgnoreCase(projectName);
        List<ProjectDto> projectsFoundByNameDto = projectsFoundByName
                .stream()
                .map(mapper::toProjectDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsFoundByNameDto);
    }

    @RequestMapping(produces = "application/json", value = "/getByAddress", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectDto>> getProjectByAddressContainsIgnoreCase(@RequestParam String projectAddress) {
        List<Project> projectsFoundByAddress = projectServiceImpl.getProjectByAddressContainsIgnoreCase(projectAddress);
        List<ProjectDto> projectsFoundByAddressDto = projectsFoundByAddress
                .stream()
                .map(mapper::toProjectDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsFoundByAddressDto);
    }

    @RequestMapping(produces = "application/json", value = "/getByCompany", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectDto>> getProjectByCompanyContainsIgnoreCase(@RequestParam String companyName) {
        List<Project> projectsFoundByCompany = projectServiceImpl.getProjectByCompanyContainsIgnoreCase(companyName);
        List<ProjectDto> projectsFoundByCompanyDto = projectsFoundByCompany
                .stream()
                .map(mapper::toProjectDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsFoundByCompanyDto);
    }

    @RequestMapping(produces = "application/json", value = "/getByProjectManager", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectDto>> getProjectByProjectManagerContainsIgnoreCase(@RequestParam String projectManagerName) {
        List<Project> projectsFoundByManager = projectServiceImpl.getProjectByProjectManagerContainsIgnoreCase(projectManagerName);
        List<ProjectDto> projectsFoundByManagerDto = projectsFoundByManager
                .stream()
                .map(mapper::toProjectDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsFoundByManagerDto);
    }

    @RequestMapping(produces = "application/json", value = "/getByContactPerson", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectDto>> getProjectByContactPersonContainsIgnoreCase(@RequestParam String contactPersonName) {
        List<Project> projectsFoundByContactPerson = projectServiceImpl.getProjectByContactPersonContainsIgnoreCase(contactPersonName);
        List<ProjectDto> projectsFoundByNameDto = projectsFoundByContactPerson
                .stream()
                .map(mapper::toProjectDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectsFoundByNameDto);
    }
}
