package the.bug.tech.brasch_management_system.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.util.Result;
import the.bug.tech.brasch_management_system.model.dto.ProjectManagerDto;
import the.bug.tech.brasch_management_system.service.EntityDtoMapper;
import the.bug.tech.brasch_management_system.service.ProjectManagerServiceImpl;
import the.bug.tech.brasch_management_system.util.Responses;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/projectManager")
public class ProjectManagerResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectManagerResource.class);

    private final ProjectManagerServiceImpl projectManagerServiceImpl;
    private final EntityDtoMapper mapper;

    @Autowired
    public ProjectManagerResource(ProjectManagerServiceImpl projectManagerServiceImpl, EntityDtoMapper mapper) {
        this.projectManagerServiceImpl = projectManagerServiceImpl;
        this.mapper = mapper;
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<ProjectManagerDto>>>> create(@RequestParam ProjectManagerDto projectManagerDto) {
        return CompletableFuture.completedFuture(projectManagerServiceImpl.insertProjectManager(mapper.toProjectManager(projectManagerDto))
                .thenApply(projectManager -> Responses.ok(projectManagerDto))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to insert project manager = {}", projectManagerDto, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.POST)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<ProjectManagerDto>>>> getProjectManagerById(@RequestParam Integer projectManagerId) {
        return CompletableFuture.completedFuture(projectManagerServiceImpl.getProjectManagerById(projectManagerId)
                .thenApply(projectManager -> {
                    ProjectManagerDto projectManagerDto = mapper.toProjectManagerDto(projectManager);
                    return Responses.ok(projectManagerDto);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project manager = {}", projectManagerId, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.POST)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<ProjectManagerDto>>>>> getAllProjectManagers() {
        return CompletableFuture.completedFuture(projectManagerServiceImpl.getAllProjectManager()
                .thenApply(projectManagers -> {
                    List<ProjectManagerDto> projectManagerDtoList = projectManagers
                            .stream()
                            .map(mapper::toProjectManagerDto)
                            .collect(Collectors.toList());
                    return Responses.ok(projectManagerDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project managers.", throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<ProjectManagerDto>>>> updateProjectManager(@RequestParam ProjectManagerDto projectManagerDto) {
        return CompletableFuture.completedFuture(projectManagerServiceImpl.updateProjectManager(mapper.toProjectManager(projectManagerDto))
                .thenApply(projectManager -> Responses.ok(projectManagerDto))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to update project manager = {}", projectManagerDto, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<Void>>>> deleteProjectManager(@RequestBody ProjectManagerDto projectManagerDto) {
        return CompletableFuture.completedFuture(projectManagerServiceImpl.deleteProjectManager(mapper.toProjectManager(projectManagerDto))
                .thenApply(Responses::ok)
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to delete project manager = {}", projectManagerDto, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "getByName", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<ProjectManagerDto>>>>> getProjectManagerByNameContainsIgnoreCase(@RequestParam String projectManagerName) {
        return CompletableFuture.completedFuture(projectManagerServiceImpl.getProjectManagerByNameContainsIgnoreCase(projectManagerName)
                .thenApply(projectManagers -> {
                    List<ProjectManagerDto> projectManagerDtoList = projectManagers
                            .stream()
                            .map(mapper::toProjectManagerDto)
                            .collect(Collectors.toList());
                    return Responses.ok(projectManagerDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to ge project manager = {}", projectManagerName, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "applicatin/json", value = "getByProject", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<ProjectManagerDto>>>>> getProjectManagerByProjectContainsIgnoreCase(@RequestParam String projectName) {
        return CompletableFuture.completedFuture(projectManagerServiceImpl.getProjectManagerByProjectContainsIgnoreCase(projectName)
                .thenApply(projectManagers -> {
                    List<ProjectManagerDto> projectManagerDtoList = projectManagers
                            .stream()
                            .map(mapper::toProjectManagerDto)
                            .collect(Collectors.toList());
                    return Responses.ok(projectManagerDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project manager = {}", projectName, throwable);
                    return Responses.internalServerError();
                }));
    }
}
