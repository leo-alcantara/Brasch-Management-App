package the.bug.tech.brasch_management_system.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.util.Result;
import the.bug.tech.brasch_management_system.model.dto.CompanyDto;
import the.bug.tech.brasch_management_system.service.CompanyServiceImpl;
import the.bug.tech.brasch_management_system.service.EntityDtoMapper;
import the.bug.tech.brasch_management_system.util.Responses;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/company")
public class CompanyResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyResource.class);

    private final CompanyServiceImpl companyServiceImpl;
    private final EntityDtoMapper mapper;

    @Autowired
    public CompanyResource(CompanyServiceImpl companyServiceImpl, EntityDtoMapper mapper) {
        this.companyServiceImpl = companyServiceImpl;
        this.mapper = mapper;
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<CompanyDto>>>> insertCompany(@RequestBody CompanyDto companyDto) {
        return CompletableFuture.completedFuture(companyServiceImpl.insertCompany(mapper.toCompany(companyDto))
                .thenApply(project -> Responses.ok(companyDto))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to insert company = {}", companyDto, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.POST)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<CompanyDto>>>> getCompanyById(@RequestParam Integer companyId) {
        return CompletableFuture.completedFuture(companyServiceImpl.getCompanyById(companyId)
                .thenApply(company -> {
                    CompanyDto companyDto = mapper.toCompanyDto(company);
                    return Responses.ok(companyDto);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get company = {}", companyId, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<CompanyDto>>>>> getAllCompanies() {
        return CompletableFuture.completedFuture(companyServiceImpl.getAllCompanies()
                .thenApply(companies -> {
                    List<CompanyDto> companyDtoList = companies
                            .stream()
                            .map(mapper::toCompanyDto)
                            .collect(Collectors.toList());
                    return Responses.ok(companyDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get companies", throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<CompanyDto>>>> updateCompany(@RequestBody CompanyDto companyDto) {
        return CompletableFuture.completedFuture(companyServiceImpl.updateCompany(mapper.toCompany(companyDto))
                .thenApply(company -> Responses.ok(companyDto))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to update company = {}", companyDto, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<Void>>>> deleteCompany(@RequestBody CompanyDto companyDto) {
        return CompletableFuture.completedFuture(companyServiceImpl.deleteCompany(mapper.toCompany(companyDto))
                .thenApply(Responses::ok)
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to delete company = {}", companyDto, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getByName", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<CompanyDto>>>>> getCompanyByCompanyNameContainsIgnoreCase(@RequestParam String companyName) {
        return CompletableFuture.completedFuture(companyServiceImpl.getCompanyByCompanyNameContainsIgnoreCase(companyName)
                .thenApply(companies -> {
                    List<CompanyDto> companyDtoList = companies
                            .stream()
                            .map(mapper::toCompanyDto)
                            .collect(Collectors.toList());
                    return Responses.ok(companyDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get company = {}", companyName, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getByProject", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<CompanyDto>>>>> getCompanyByProjectNameContainsIgnoreCase(@RequestParam String projectName) {
        return CompletableFuture.completedFuture(companyServiceImpl.getCompanyByProjectNameContainsIgnoreCase(projectName)
                .thenApply(companies -> {
                    List<CompanyDto> companyDtoList = companies
                            .stream()
                            .map(mapper::toCompanyDto)
                            .collect(Collectors.toList());
                    return Responses.ok(companyDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get company = {}", projectName, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getByContactPerson", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<CompanyDto>>>>> getCompanyByContactPersonContainsIgnoreCase(@RequestParam String contactPersonName) {
        return CompletableFuture.completedFuture(companyServiceImpl.getCompanyByContactPersonContainsIgnoreCase(contactPersonName)
                .thenApply(companies -> {
                    List<CompanyDto> companyDtoList = companies
                            .stream()
                            .map(mapper::toCompanyDto)
                            .collect(Collectors.toList());
                    return Responses.ok(companyDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get company = {}", contactPersonName, throwable);
                    return Responses.internalServerError();
                }));
    }
}
