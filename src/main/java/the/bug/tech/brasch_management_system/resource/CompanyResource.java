package the.bug.tech.brasch_management_system.resource;

import io.swagger.annotations.ApiOperation;
import io.vavr.control.Option;
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
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/company")
public class CompanyResource {

    private static final Logger logger = LoggerFactory.getLogger(CompanyResource.class);

    private final CompanyServiceImpl companyServiceImpl;
    private final EntityDtoMapper converter;

    @Autowired
    public CompanyResource(CompanyServiceImpl companyServiceImpl, EntityDtoMapper converter) {
        this.companyServiceImpl = companyServiceImpl;
        this.converter = converter;
    }

    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    @ApiOperation(value = "Persists company instances", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<String>>> insertCompany(@RequestBody CompanyDto companyDto) {
        return companyServiceImpl.insertCompany(converter.toCompany(companyDto))
                .thenApply(project -> Responses.ok(""))
                .exceptionally(throwable -> {
                    logger.error("Failed to insert company = {}", companyDto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/companyId", method = RequestMethod.POST)
    @ApiOperation(value = "Gets company instance by id", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<CompanyDto>>> getCompanyById(@RequestParam Optional<String> companyIdOpt) {
        return companyServiceImpl.getCompanyById(companyIdOpt.get()).thenApply(company -> {CompanyDto companyDto = converter.toCompanyDto(company);
            return Responses.ok(companyDto);
        }).exceptionally(throwable -> {
            logger.error("Failed to get company = {}", companyIdOpt, throwable);
            return Responses.internalServerError();
        });
    }

    @RequestMapping(produces = "application/json", value = "/findAll", method = RequestMethod.GET)
    @ApiOperation(value = "Gets all company instances", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<List<CompanyDto>>>> getAllCompanies() {
        return companyServiceImpl.getAllCompanies().thenApply(companies -> {
            List<CompanyDto> companyDtoList = companies.stream()
                    .map(converter::toCompanyDto)
                    .collect(Collectors.toList());
            return Responses.ok(companyDtoList);
        }).exceptionally(throwable -> {
            logger.error("Failed to get companies = {}", throwable);
            return Responses.internalServerError();
        });
    }

    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    @ApiOperation(value = "Updates company instances", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<String>>> updateCompany(@RequestBody CompanyDto companyDto) {
        return companyServiceImpl.updateCompany(converter.toCompany(companyDto))
                .thenApply(company -> Responses.ok(""))
                .exceptionally(throwable -> {
                    logger.error("Failed to update company = {}", companyDto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    @ApiOperation(value = "Deletes company instances", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<Option<Void>>>> deleteCompany(@RequestBody CompanyDto companyDto) {
        return companyServiceImpl.deleteCompany(converter.toCompany(companyDto))
                .thenApply(Responses::ok)
                .exceptionally(throwable -> {
                    logger.error("Failed to delete company = {}", companyDto, throwable);
                    return Responses.internalServerError();
                });
    }


    @RequestMapping(produces = "application/json", value = "/getByName", method = RequestMethod.GET)
    @ApiOperation(value = "Gets company instances by name", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<CompanyDto>>> getCompanyByCompanyNameContainsIgnoreCase(@RequestParam String companyName) {
        return companyServiceImpl.getCompanyByCompanyNameContainsIgnoreCase(companyName)
                .thenApply(company -> {
                    CompanyDto companyDto = converter.toCompanyDto(company.get());
                    return Responses.ok(companyDto);
                }).exceptionally(throwable -> {
                    logger.error("Failed to get company = {}", companyName, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getByProject", method = RequestMethod.GET)
    @ApiOperation(value = "Gets company instances by project", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<CompanyDto>>> getCompanyByProjectNameContainsIgnoreCase(@RequestParam String projectName) {
        return companyServiceImpl.getCompanyByProjectNameContainsIgnoreCase(projectName)
                .thenApply(company -> {
                    CompanyDto companyDto = converter.toCompanyDto(company.get());
                    return Responses.ok(companyDto);
                }).exceptionally(throwable -> {
                    logger.error("Failed to get company = {}", projectName, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getByContactPerson", method = RequestMethod.GET)
    @ApiOperation(value = "Gets company instances by contact person", response = CompanyDto.class)
    public CompletionStage<ResponseEntity<Result<CompanyDto>>> findCompanyByContactPersonContainsIgnoreCase(@RequestParam String contactPersonName) {
        return companyServiceImpl.getCompanyByContactPersonContainsIgnoreCase(contactPersonName)
                .thenApply(company -> {
                    CompanyDto companyDto = converter.toCompanyDto(company.get());
                    return Responses.ok(companyDto);
                }).exceptionally(throwable -> {
                    logger.error("Failed to get company = {}", contactPersonName, throwable);
                    return Responses.internalServerError();
                });
    }
}
