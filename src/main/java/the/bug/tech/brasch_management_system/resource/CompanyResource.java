package the.bug.tech.brasch_management_system.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.dto.Result;
import the.bug.tech.brasch_management_system.model.dto.CompanyDto;
import the.bug.tech.brasch_management_system.service.CompanyServiceImpl;
import the.bug.tech.brasch_management_system.service.EntityDtoMapper;
import the.bug.tech.brasch_management_system.util.Responses;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/company")
public class CompanyResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyResource.class);

    private final CompanyServiceImpl COMPANY_SERVICE_IMPL;
    private final EntityDtoMapper CONVERTER;

    @Autowired
    public CompanyResource(CompanyServiceImpl COMPANY_SERVICE_IMPL, EntityDtoMapper CONVERTER) {
        this.COMPANY_SERVICE_IMPL = COMPANY_SERVICE_IMPL;
        this.CONVERTER = CONVERTER;
    }

    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    //@ApiOperation(value = "Inserts company instance", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<String>>> insertCompany(@RequestBody CompanyDto companyDto) {
        return COMPANY_SERVICE_IMPL.insertCompany(CONVERTER.toCompany(companyDto))
                .thenApply(project -> Responses.ok(""))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to insert company = {}", companyDto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.POST)
    //@ApiOperation(value = "Gets company instance by id", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<CompanyDto>>> getCompanyById(@RequestParam String companyId) {
        return COMPANY_SERVICE_IMPL.getCompanyById(companyId)
                .thenApply(company -> {
                    CompanyDto companyDto = CONVERTER.toCompanyDto(company);
                    return Responses.ok(companyDto);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get company = {}", companyId, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets all company instances", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<List<CompanyDto>>>> getAllCompanies() {
        return COMPANY_SERVICE_IMPL.getAllCompanies()
                .thenApply(companies -> {
                    List<CompanyDto> companyDtoList = companies
                            .stream()
                            .map(CONVERTER::toCompanyDto)
                            .collect(Collectors.toList());
                    return Responses.ok(companyDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get companies = {}", throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    //@ApiOperation(value = "Updates company instance", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<String>>> updateCompany(@RequestBody CompanyDto companyDto) {
        return COMPANY_SERVICE_IMPL.updateCompany(CONVERTER.toCompany(companyDto))
                .thenApply(company -> Responses.ok(""))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to update company = {}", companyDto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    //@ApiOperation(value = "Deletes company instance", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<Void>>> deleteCompany(@RequestBody CompanyDto companyDto) {
        return COMPANY_SERVICE_IMPL.deleteCompany(CONVERTER.toCompany(companyDto))
                .thenApply(Responses::ok)
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to delete company = {}", companyDto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getByName", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets company instance by name", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<CompanyDto>>> getCompanyByCompanyNameContainsIgnoreCase(@RequestParam String companyName) {
        return COMPANY_SERVICE_IMPL.getCompanyByCompanyNameContainsIgnoreCase(companyName)
                .thenApply(company -> {
                    CompanyDto companyDto = CONVERTER.toCompanyDto(company);
                    return Responses.ok(companyDto);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get company = {}", companyName, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getByProject", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets company instance by project", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<CompanyDto>>> getCompanyByProjectNameContainsIgnoreCase(@RequestParam String projectName) {
        return COMPANY_SERVICE_IMPL.getCompanyByProjectNameContainsIgnoreCase(projectName)
                .thenApply(company -> {
                    CompanyDto companyDto = CONVERTER.toCompanyDto(company);
                    return Responses.ok(companyDto);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get company = {}", projectName, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getByContactPerson", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets company instance by contact person", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<CompanyDto>>> getCompanyByContactPersonContainsIgnoreCase(@RequestParam String contactPersonName) {
        return COMPANY_SERVICE_IMPL.getCompanyByContactPersonContainsIgnoreCase(contactPersonName)
                .thenApply(company -> {
                    CompanyDto companyDto = CONVERTER.toCompanyDto(company);
                    return Responses.ok(companyDto);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get company = {}", contactPersonName, throwable);
                    return Responses.internalServerError();
                });
    }
}
