package the.bug.tech.brasch_management_system.resource;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.model.dto.ProjectDto;
import the.bug.tech.brasch_management_system.service.EntityDtoMapper;
import the.bug.tech.brasch_management_system.service.ProjectServiceImpl;
import the.bug.tech.brasch_management_system.util.Responses;
import the.bug.tech.brasch_management_system.util.Result;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/project")
public class ProjectResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectResource.class);

    private final ProjectServiceImpl projectServiceImpl;
    private final EntityDtoMapper mapper;

    @Autowired
    public ProjectResource(ProjectServiceImpl projectServiceImpl, EntityDtoMapper mapper) {
        this.projectServiceImpl = projectServiceImpl;
        this.mapper = mapper;
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<ProjectDto>>>> insertProject(@RequestBody ProjectDto projectdto) {
        return CompletableFuture.completedFuture(projectServiceImpl.insertProject(mapper.toProject(projectdto))
                .thenApply(project -> Responses.ok(projectdto))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to insert project = {}", projectdto, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<ProjectDto>>>> getProjectById(@RequestParam Integer projectId) {

        return CompletableFuture.completedFuture(projectServiceImpl.getProjectById(projectId).thenApply(project -> {
            ProjectDto projectDto = mapper.toProjectDto(project);
            return Responses.ok(projectDto);
        }).exceptionally(throwable -> {
            LOGGER.error("Failed to get project = {}", projectId, throwable);
            return Responses.internalServerError();
        }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<ProjectDto>>>>> getAllProjects() {
        return CompletableFuture.completedFuture(projectServiceImpl.getAllProject().thenApply(projects -> {
            List<ProjectDto> projectDtoList = projects
                    .stream()
                    .map(mapper::toProjectDto)
                    .collect(Collectors.toList());
            return Responses.ok(projectDtoList);
        }).exceptionally(throwable -> {
            LOGGER.error("Failed to get projects", throwable);
            return Responses.internalServerError();
        }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<ProjectDto>>>> updateProject(@RequestBody ProjectDto projectDto) {
        return CompletableFuture.completedFuture(projectServiceImpl.updateProject(mapper.toProject(projectDto))
                .thenApply(project -> Responses.ok(projectDto))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to update project = {}", projectDto, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<Void>>>> deleteProject(@RequestBody ProjectDto projectDto) {
        return CompletableFuture.completedFuture(projectServiceImpl.deleteProject(mapper.toProject(projectDto))
                .thenApply(Responses::ok)
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to delete project = {}", projectDto, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getByName", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<ProjectDto>>>>> getProjectByNameContainsIgnoreCase(@RequestParam String projectName) {

        return CompletableFuture.completedFuture(projectServiceImpl.getProjectByNameContainsIgnoreCase(projectName)
                .thenApply(projects -> {
                    List<ProjectDto> projectDtoList = projects
                            .stream()
                            .map(mapper::toProjectDto)
                            .collect(Collectors.toList());
                    return Responses.ok(projectDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project = {}", projectName, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getByAddress", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<ProjectDto>>>>> getProjectByAddressContainsIgnoreCase(@RequestParam String projectAddress) {
        return CompletableFuture.completedFuture(projectServiceImpl.getProjectByAddressContainsIgnoreCase(projectAddress)
                .thenApply(projects -> {
                    List<ProjectDto> projectDtoList = projects
                            .stream()
                            .map(mapper::toProjectDto)
                            .collect(Collectors.toList());
                    return Responses.ok(projectDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project = {}", projectAddress, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getByCompany", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<ProjectDto>>>>> getProjectByCompanyContainsIgnoreCase(@RequestParam String companyName) {
        return CompletableFuture.completedFuture(projectServiceImpl.getProjectByCompanyContainsIgnoreCase(companyName)
                .thenApply(projects -> {
                    List<ProjectDto> projectDtoList = projects
                            .stream()
                            .map(mapper::toProjectDto)
                            .collect(Collectors.toList());
                    return Responses.ok(projectDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project = {}", companyName, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getByProjectManager", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<ProjectDto>>>>> getProjectByProjectManagerContainsIgnoreCase(@RequestParam String projectManagerName) {
        return CompletableFuture.completedFuture(projectServiceImpl.getProjectByProjectManagerContainsIgnoreCase(projectManagerName)
                .thenApply(projects -> {
                    List<ProjectDto> projectDtoList = projects
                            .stream()
                            .map(mapper::toProjectDto)
                            .collect(Collectors.toList());
                    return Responses.ok(projectDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project = {}", projectManagerName, throwable);
                    return Responses.internalServerError();
                }));
    }

    @RequestMapping(produces = "application/json", value = "/getByContactPerson", method = RequestMethod.GET)
    @ApiOperation(value = "Gets project instance by contact person", response = ProjectDto.class)
    public CompletionStage<ResponseEntity<Result<List<ProjectDto>>>> getProjectByContactPersonContainsIgnoreCase(@RequestParam String contactPersonName) {
        return projectServiceImpl.getProjectByContactPersonContainsIgnoreCase(contactPersonName)
                .thenApply(projects -> {
                    List<ProjectDto> projectDtoList = projects
                            .stream()
                            .map(mapper::toProjectDto)
                            .collect(Collectors.toList());
                    return Responses.ok(projectDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project = {}", contactPersonName, throwable);
                    return Responses.invalidArgument();
                });
    }
}
