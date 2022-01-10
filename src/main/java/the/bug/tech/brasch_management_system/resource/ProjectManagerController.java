package the.bug.tech.brasch_management_system.resource;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.model.ProjectManager;
import the.bug.tech.brasch_management_system.service.ProjectManagerService;

import java.util.List;
import java.util.concurrent.CompletionStage;

@RestController
public class ProjectManagerController {

    private final ProjectManagerService projectManagerService;

    @Autowired
    public ProjectManagerController(ProjectManagerService projectManagerService) {
        this.projectManagerService = projectManagerService;
    }
    
    @PostMapping("/api/project-manager")
    public ResponseEntity<ProjectManager> create(@RequestParam ProjectManager projectManager){
        ProjectManager saved= projectManagerService.createProjectManager(projectManager);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/api/project-manager/{id}")
    public ResponseEntity<ProjectManager> findById(@PathVariable("id") String projectManagerId){
        ProjectManager foundById = projectManagerService.findById(projectManagerId);
        return ResponseEntity.ok(foundById);
    }

    @GetMapping("/api/project-manager")
    public ResponseEntity<List<ProjectManager>> findAll(){
        List<ProjectManager> allFound = projectManagerService.findAll();
        return ResponseEntity.ok(allFound);
    }

    @PutMapping("/api/project-manager/{id}")
    public ResponseEntity<ProjectManager> update(@PathVariable("id") String projectManagerId,
                                                 @RequestBody ProjectManager projectManager){
        if(projectManagerId.equals(projectManager.getProjectManagerId())){
            ProjectManager updated= projectManagerService.update(projectManager);
            return ResponseEntity.ok().body(updated);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/project-manager/{id}")
    public ResponseEntity<ProjectManager> delete(@PathVariable("id") String projectManagerId){
        projectManagerService.delete(projectManagerId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/project-manager/{projectManagerName}")
    public ResponseEntity<CompletionStage<Option<ProjectManager>>> findProjectManagerByNameContainsIgnoreCase(@PathVariable("projectManagerName") String projectManagerName){
        CompletionStage<Option<ProjectManager>> found= projectManagerService.findProjectManagerByNameContainsIgnoreCase(projectManagerName);
        return ResponseEntity.ok(found);
    }


    @GetMapping("/api/project-manager/{projectName}")
    public ResponseEntity<CompletionStage<Option<ProjectManager>>> findProjectManagerByProjectContainsIgnoreCase(@PathVariable("projectName") String projectName){
         CompletionStage<Option<ProjectManager>> found= projectManagerService.findProjectManagerByProjectContainsIgnoreCase(projectName);
         return ResponseEntity.ok(found);
    }



}
