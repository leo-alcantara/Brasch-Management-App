package the.bug.tech.brasch_management_system.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.model.ProjectManager;
import the.bug.tech.brasch_management_system.model.dto.ProjectManagerDto;
import the.bug.tech.brasch_management_system.service.EntityDtoMapper;
import the.bug.tech.brasch_management_system.service.ProjectManagerServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/projectManager")
public class ProjectManagerResource {

    private final ProjectManagerServiceImpl projectManagerServiceImpl;
    private final EntityDtoMapper mapper;

    @Autowired
    public ProjectManagerResource(ProjectManagerServiceImpl projectManagerServiceImpl, EntityDtoMapper mapper) {
        this.projectManagerServiceImpl = projectManagerServiceImpl;
        this.mapper = mapper;
    }

    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ProjectManagerDto> insertProjectManager(@RequestBody ProjectManagerDto projectManagerDto) {
        ProjectManager insertedProjectManager = projectManagerServiceImpl.insertProjectManager(mapper.toProjectManager(projectManagerDto));
        ProjectManagerDto insertedProjectManagerDto = mapper.toProjectManagerDto(insertedProjectManager);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedProjectManagerDto);
    }

    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<ProjectManagerDto> getProjectManagerById(@RequestParam Integer projectManagerId) {
        ProjectManager PMFoundById = projectManagerServiceImpl.getProjectManagerById(projectManagerId);
        ProjectManagerDto PMFoundByIdDto = mapper.toProjectManagerDto(PMFoundById);
        return ResponseEntity.ok(PMFoundByIdDto);
    }

    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectManagerDto>> getAllProjectManagers() {
        List<ProjectManager> projectManagers = projectManagerServiceImpl.getAllProjectManager();
        List<ProjectManagerDto> projectManagersDto = projectManagers
                .stream()
                .map(mapper::toProjectManagerDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectManagersDto);

    }

    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ProjectManagerDto> updateProjectManager(Integer projectManagerToUpdateId,
                                                                  @RequestBody ProjectManagerDto projectManagerDto) {
        ProjectManager convertedProjectManager = projectManagerServiceImpl.updateProjectManager(projectManagerToUpdateId, mapper.toProjectManager(projectManagerDto));
        ProjectManagerDto convertedProjectManagerDto = mapper.toProjectManagerDto(convertedProjectManager);
        return ResponseEntity.ok().body(convertedProjectManagerDto);
    }

    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteProjectManager(@RequestBody Integer projectManagerId) {
        projectManagerServiceImpl.deleteProjectManager(projectManagerId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(produces = "application/json", value = "getByName", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectManagerDto>> getProjectManagerByNameContainsIgnoreCase(@RequestParam String projectManagerName) {
        List<ProjectManager> projectManagersFoundByName = projectManagerServiceImpl.getProjectManagerByNameContainsIgnoreCase(projectManagerName);
        List<ProjectManagerDto> projectManagersFoundByNameDto = projectManagersFoundByName
                .stream()
                .map(mapper::toProjectManagerDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectManagersFoundByNameDto);
    }

    @RequestMapping(produces = "applicatin/json", value = "getByProject", method = RequestMethod.GET)
    public ResponseEntity<List<ProjectManagerDto>> getProjectManagerByProjectContainsIgnoreCase(@RequestParam String projectName) {
        List<ProjectManager> projectManagersFoundByProject = projectManagerServiceImpl.getProjectManagerByProjectContainsIgnoreCase(projectName);
        List<ProjectManagerDto> projectManagersFoundByProjectDto = projectManagersFoundByProject
                .stream()
                .map(mapper::toProjectManagerDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(projectManagersFoundByProjectDto);
    }
}
