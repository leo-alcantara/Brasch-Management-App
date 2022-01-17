package the.bug.tech.brasch_management_system.resource;

import io.vavr.control.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.dto.Result;
import the.bug.tech.brasch_management_system.model.dto.ProjectDto;
import the.bug.tech.brasch_management_system.service.EntityDtoMapper;
import the.bug.tech.brasch_management_system.service.ProjectServiceImpl;
import the.bug.tech.brasch_management_system.util.Responses;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/project")
public class ProjectResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectResource.class);

    private final ProjectServiceImpl PROJECT_SERVICE_IMPL;
    private final EntityDtoMapper CONVERTER;

    @Autowired
    public ProjectResource(ProjectServiceImpl PROJECT_SERVICE_IMPL, EntityDtoMapper CONVERTER) {
        this.PROJECT_SERVICE_IMPL = PROJECT_SERVICE_IMPL;
        this.CONVERTER = CONVERTER;
    }

    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    //@ApiOperation(value = "Persists project instance", response = ProjectDto.class)
    public CompletionStage<ResponseEntity<Result<String>>> insertProject(@RequestBody ProjectDto projectdto) {
        return PROJECT_SERVICE_IMPL.insertProject(CONVERTER.toProject(projectdto))
                .thenApply(project -> Responses.ok(""))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to insert project = {}", projectdto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets project instance by id", response = ProjectDto.class)
    public CompletionStage<ResponseEntity<Result<ProjectDto>>> getProjectById(@RequestParam String projectId) {

        return PROJECT_SERVICE_IMPL.getProjectById(projectId).thenApply(project -> {
            ProjectDto projectDto = CONVERTER.toProjectDto(project);
            return Responses.ok(projectDto);
        }).exceptionally(throwable -> {
            LOGGER.error("Failed to get project = {}", projectId, throwable);
            return Responses.internalServerError();
        });
    }

    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets all project instances", response = ProjectDto.class)
    public CompletionStage<ResponseEntity<Result<List<ProjectDto>>>> getAllProjects() {
        return PROJECT_SERVICE_IMPL.getAllProject().thenApply(projects -> {
            List<ProjectDto> projectDtoList = projects.stream()
                    .map(CONVERTER::toProjectDto)
                    .collect(Collectors.toList());
            return Responses.ok(projectDtoList);
        }).exceptionally(throwable -> {
            LOGGER.error("Failed to get projects = {}", throwable);
            return Responses.internalServerError();
        });
    }

    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    //@ApiOperation(value = "Updates project instance", response = ProjectDto.class)
    public CompletionStage<ResponseEntity<Result<String>>> updateProject(@RequestBody ProjectDto projectDto) {
        return PROJECT_SERVICE_IMPL.updateProject(CONVERTER.toProject(projectDto))
                .thenApply(project -> Responses.ok(""))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to update project = {}", projectDto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    //@ApiOperation(value = "Deletes project instance", response = ProjectDto.class)
    public CompletionStage<ResponseEntity<Result<Option<Void>>>> deleteProject(@RequestBody ProjectDto projectDto) {
        return PROJECT_SERVICE_IMPL.deleteProject(CONVERTER.toProject(projectDto))
                .thenApply(Responses::ok)
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to delete project = {}", projectDto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getByName", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets project instance by name", response = ProjectDto.class)
    public CompletionStage<ResponseEntity<Result<ProjectDto>>> getProjectByNameContainsIgnoreCase(@RequestParam String projectName) {

        return PROJECT_SERVICE_IMPL.getProjectByNameContainsIgnoreCase(projectName)
                .thenApply(project -> {
                    ProjectDto projectDto = CONVERTER.toProjectDto(project);
                    return Responses.ok(projectDto);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project = {}", projectName, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getByAddress", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets project instance by address", response = ProjectDto.class)
    public CompletionStage<ResponseEntity<Result<ProjectDto>>> getProjectByAddressContainsIgnoreCase(@RequestParam String projectAddress) {
        return PROJECT_SERVICE_IMPL.getProjectByAddressContainsIgnoreCase(projectAddress)
                .thenApply(project -> {
                    ProjectDto projectDto = CONVERTER.toProjectDto(project);
                    return Responses.ok(projectDto);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project = {}", projectAddress, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getByCompany", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets project instance by company", response = ProjectDto.class)
    public CompletionStage<ResponseEntity<Result<List<ProjectDto>>>> getProjectByCompanyContainsIgnoreCase(@RequestParam String companyName) {
        return PROJECT_SERVICE_IMPL.getProjectByCompanyContainsIgnoreCase(companyName)
                .thenApply(projects -> {
                    List<ProjectDto> projectDtoList = projects.stream()
                            .map(CONVERTER::toProjectDto)
                            .collect(Collectors.toList());
                    return Responses.ok(projectDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project = {}", companyName, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getByProjectManager", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets project instance by project manager", response = ProjectDto.class)
    public CompletionStage<ResponseEntity<Result<List<ProjectDto>>>> getProjectByProjectManagerContainsIgnoreCase(@RequestParam String projectManagerName) {
        return PROJECT_SERVICE_IMPL.getProjectByProjectManagerContainsIgnoreCase(projectManagerName)
                .thenApply(projects -> {
                    List<ProjectDto> projectDtoList = projects.stream()
                            .map(CONVERTER::toProjectDto)
                            .collect(Collectors.toList());
                    return Responses.ok(projectDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project = {}", projectManagerName, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getByContactPerson", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets project instance by contact person", response = ProjectDto.class)
    public CompletionStage<ResponseEntity<Result<List<ProjectDto>>>> getProjectByContactPersonContainsIgnoreCase(@RequestParam String contactPersonName) {
        return PROJECT_SERVICE_IMPL.getProjectByContactPersonContainsIgnoreCase(contactPersonName)
                .thenApply(projects -> {
                    List<ProjectDto> projectDtoList = projects.stream()
                            .map(CONVERTER::toProjectDto)
                            .collect(Collectors.toList());
                    return Responses.ok(projectDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get project = {}", contactPersonName, throwable);
                    return Responses.internalServerError();
                });
    }
}
