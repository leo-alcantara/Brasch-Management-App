package the.bug.tech.brasch_management_system.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.dto.Result;
import the.bug.tech.brasch_management_system.model.dto.ProjectManagerDto;
import the.bug.tech.brasch_management_system.service.EntityDtoMapper;
import the.bug.tech.brasch_management_system.service.ProjectManagerServiceImpl;
import the.bug.tech.brasch_management_system.util.Responses;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/projectManager")
public class ProjectManagerResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectManagerResource.class);

    private final ProjectManagerServiceImpl PROJECT_MANAGER_SERVICE_IMPL;
    private final EntityDtoMapper CONVERTER;

    @Autowired
    public ProjectManagerResource(ProjectManagerServiceImpl PROJECT_MANAGER_SERVICE_IMPL, EntityDtoMapper CONVERTER) {
        this.PROJECT_MANAGER_SERVICE_IMPL = PROJECT_MANAGER_SERVICE_IMPL;
        this.CONVERTER = CONVERTER;
    }

    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    //@ApiOperation(value = "Inserts project manager", response = ProjectManagerDto.class)
    public CompletionStage<ResponseEntity<Result<String>>> create(@RequestParam ProjectManagerDto projectManagerDto) {
        return PROJECT_MANAGER_SERVICE_IMPL.insertProjectManager(CONVERTER.toProjectManager(projectManagerDto))
                .thenApply(projectManager -> Responses.ok(""))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to insert project manager = {}", projectManagerDto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.POST)
    //@ApiOperation(value = "Gets project manager instance by id", response = ProjectManagerDto.class)
    public CompletionStage<ResponseEntity<Result<ProjectManagerDto>>> getProjectManagerById(@RequestParam String projectManagerId) {
        return PROJECT_MANAGER_SERVICE_IMPL.getProjectManagerById(projectManagerId)
                .thenApply(projectManager -> {
                    ProjectManagerDto projectManagerDto = CONVERTER.toProjectManagerDto(projectManager);
                    return Responses.ok(projectManagerDto);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project manager = {}", projectManagerId, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.POST)
    //@ApiOperation(value = "Gets all project managers instances", response = ProjectManagerDto.class)
    public CompletionStage<ResponseEntity<Result<List<ProjectManagerDto>>>> getAllProjectManagers() {
        return PROJECT_MANAGER_SERVICE_IMPL.getAllProjectManager()
                .thenApply(projectManagers -> {
                    List<ProjectManagerDto> projectManagerDtoList = projectManagers
                            .stream()
                            .map(CONVERTER::toProjectManagerDto)
                            .collect(Collectors.toList());
                    return Responses.ok(projectManagerDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project managers = {}", throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    //@ApiOperation(value = "Updates project manager instance", response = ProjectManagerDto.class)
    public CompletionStage<ResponseEntity<Result<String>>> updateProjectManager(@RequestParam ProjectManagerDto projectManagerDto) {
        return PROJECT_MANAGER_SERVICE_IMPL.updateProjectManager(CONVERTER.toProjectManager(projectManagerDto))
                .thenApply(projectManager -> Responses.ok(""))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to update project manager = {}", projectManagerDto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    //@ApiOperation(value = "Deletes project manager instance", response = ProjectManagerDto.class)
    public CompletionStage<ResponseEntity<Result<Void>>> deleteProjectManager(@RequestBody ProjectManagerDto projectManagerDto) {
        return PROJECT_MANAGER_SERVICE_IMPL.deleteProjectManager(CONVERTER.toProjectManager(projectManagerDto))
                .thenApply(Responses::ok)
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to delete project manager = {}", projectManagerDto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "getByName", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets project manager instance by name", response = ProjectManagerDto.class)
    public CompletionStage<ResponseEntity<Result<ProjectManagerDto>>> getProjectManagerByNameContainsIgnoreCase(@RequestParam String projectManagerName) {
        return PROJECT_MANAGER_SERVICE_IMPL.getProjectManagerByNameContainsIgnoreCase(projectManagerName)
                .thenApply(projectManager -> {
                    ProjectManagerDto projectManagerDto = CONVERTER.toProjectManagerDto(projectManager);
                    return Responses.ok(projectManagerDto);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to ge project manager = {}", projectManagerName, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "applicatin/json", value = "getByProject", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets project manager instance by project", response = ProjectManagerDto.class)
    public CompletionStage<ResponseEntity<Result<ProjectManagerDto>>> getProjectManagerByProjectContainsIgnoreCase(@RequestParam String projectName) {
        return PROJECT_MANAGER_SERVICE_IMPL.getProjectManagerByProjectContainsIgnoreCase(projectName)
                .thenApply(projectManager -> {
                    ProjectManagerDto projectManagerDto = CONVERTER.toProjectManagerDto(projectManager);
                    return Responses.ok(projectManagerDto);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project manager = {}", projectName, throwable);
                    return Responses.internalServerError();
                });
    }
}
