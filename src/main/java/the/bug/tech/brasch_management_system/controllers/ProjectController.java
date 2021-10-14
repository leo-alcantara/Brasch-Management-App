package the.bug.tech.brasch_management_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.model.Project;
import the.bug.tech.brasch_management_system.service.ProjectService;

import java.util.List;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @PostMapping("/api/projects")
    public ResponseEntity<Project> create(@RequestBody Project project) {
        Project saved = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/api/projects/{id}")
    public ResponseEntity<Project> findById(@PathVariable("id") String projectId) {
        Project projectFoundById = projectService.findById(projectId);
        return ResponseEntity.ok(projectFoundById);
    }

    @GetMapping("/api/projects")
    public ResponseEntity<List<Project>> findAll() {
        List<Project> allFound = projectService.findAll();
        return ResponseEntity.ok(allFound);
    }

    @PutMapping("/api/projects/{id}")
    public ResponseEntity<Project> update(@PathVariable("id") String projectId,
                                          @RequestBody Project project) {
        if (projectId.equals(project.getProjectId())) {
            Project updatedProject = projectService.update(project);
            return ResponseEntity.ok().body(updatedProject);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/projects/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String projectId) {
        projectService.delete(projectId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/projects/{projectName}")
    public ResponseEntity<Project> findProjectByNameContainsIgnoreCase(@PathVariable("projectName") String projectName){
        Project foundProject = projectService.findProjectByNameContainsIgnoreCase(projectName);
        return ResponseEntity.ok(foundProject);
    }

    @GetMapping("/api/projects/{projectAddress}")
    public ResponseEntity<Project> findProjectByAddressContainsIgnoreCase(@PathVariable("projectAddress") String projectAddress){
        Project projectFound= projectService.findProjectByAddressContainsIgnoreCase(projectAddress);
        return ResponseEntity.ok(projectFound);
    }

    @GetMapping("/api/projects/{companyName}")
    public ResponseEntity<List<Project>> findProjectByCompanyContainsIgnoreCase(@PathVariable("companyName") String companyName){
        List<Project> foundProjects= projectService.findProjectByCompanyContainsIgnoreCase(companyName);
        return ResponseEntity.ok(foundProjects);
    }

    @GetMapping("/api/projects/{projectManagerName}")
    public ResponseEntity<List<Project>> findProjectByProjectManagerContainsIgnoreCase(@PathVariable("projectManagerName") String name){
        List<Project> foundProjects= projectService.findProjectByProjectManagerContainsIgnoreCase(name);
        return ResponseEntity.ok(foundProjects);
    }

    @GetMapping("/api/projects/{contactPersonName}")
    public ResponseEntity<List<Project>> findProjectByContactPersonContainsIgnoreCase(@PathVariable("contactPersonName") String name){
        List<Project> foundProjects= projectService.findProjectByContactPersonContainsIgnoreCase(name);
        return ResponseEntity.ok(foundProjects);
    }













}
