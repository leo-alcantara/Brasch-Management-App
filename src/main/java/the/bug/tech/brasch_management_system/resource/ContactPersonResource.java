package the.bug.tech.brasch_management_system.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.model.ContactPerson;
import the.bug.tech.brasch_management_system.model.dto.ContactPersonDto;
import the.bug.tech.brasch_management_system.service.ContactPersonServiceImpl;
import the.bug.tech.brasch_management_system.service.EntityDtoMapper;
import the.bug.tech.brasch_management_system.util.Responses;
import the.bug.tech.brasch_management_system.util.Result;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/contactPerson")
public class ContactPersonResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactPerson.class);

    private final ContactPersonServiceImpl contactPersonServiceImpl;
    private final EntityDtoMapper mapper;

    @Autowired
    public ContactPersonResource(ContactPersonServiceImpl contactPersonServiceImpl, EntityDtoMapper mapper) {
        this.contactPersonServiceImpl = contactPersonServiceImpl;
        this.mapper = mapper;
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<ContactPersonDto>>>> create(@RequestBody ContactPersonDto contactPersonDto) {
        return CompletableFuture.completedFuture(contactPersonServiceImpl.insertContactPerson(mapper.toContactPerson(contactPersonDto))
                .thenApply(project -> Responses.ok(contactPersonDto))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to insert contact person = {}", contactPersonDto, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.POST)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<ContactPersonDto>>>> getContactPersonById(@RequestParam Integer contactPersonId) {
        return CompletableFuture.completedFuture(contactPersonServiceImpl.getContactPersonById(contactPersonId)
                .thenApply(contactPerson -> {
                    ContactPersonDto contactPersonDto = mapper.toContactPersonDto(contactPerson);
                    return Responses.ok(contactPersonDto);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get contact person = {}", contactPersonId, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<ContactPersonDto>>>>> getAllContactPerson() {
        return CompletableFuture.completedFuture(contactPersonServiceImpl.getAllContactPerson()
                .thenApply(contactPersons -> {
            List<ContactPersonDto> contactPersonDtoList = contactPersons
                    .stream()
                    .map(mapper::toContactPersonDto)
                    .collect(Collectors.toList());
            return Responses.ok(contactPersonDtoList);
        }).exceptionally(throwable -> {
            LOGGER.error("Failed to get contact person", throwable);
            return Responses.internalServerError();
        }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<ContactPersonDto>>>> updateContactPerson(@RequestBody ContactPersonDto contactPersonDto) {
        return CompletableFuture.completedFuture(contactPersonServiceImpl.updateContactPerson(mapper.toContactPerson(contactPersonDto))
                .thenApply(company -> Responses.ok(contactPersonDto))
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to update contact person = {}", contactPersonDto, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<Void>>>> deleteContactPerson(@RequestBody ContactPersonDto contactPersonDto) {
        return CompletableFuture.completedFuture(contactPersonServiceImpl.deleteContactPerson(mapper.toContactPerson(contactPersonDto))
                .thenApply(Responses::ok)
                .exceptionally(throwable -> {
                    LOGGER.error("Failed to delete contact person = {}", contactPersonDto, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getByName", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<ContactPersonDto>>>>> getContactPersonByNameContainsIgnoreCase(@RequestParam String contactPersonName) {
        return CompletableFuture.completedFuture(contactPersonServiceImpl.getContactPersonByNameContainsIgnoreCase(contactPersonName)
                .thenApply(contactPersons -> {
                    List<ContactPersonDto> contactPersonDtoList = contactPersons
                            .stream()
                            .map(mapper::toContactPersonDto)
                            .collect(Collectors.toList());
                    return Responses.ok(contactPersonDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get company = {}", contactPersonName, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getByCompany", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<ContactPersonDto>>>>> getContactPersonByCompanyContainsIgnoreCase(@RequestParam String companyName) {
        return CompletableFuture.completedFuture(contactPersonServiceImpl.getContactPersonByCompanyContainsIgnoreCase(companyName)
                .thenApply(contactPersons -> {
                    List<ContactPersonDto> contactPersonDtoList = contactPersons
                            .stream()
                            .map(mapper::toContactPersonDto)
                            .collect(Collectors.toList());
                    return Responses.ok(contactPersonDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get contact person = {}", companyName, throwable);
                    return Responses.internalServerError();
                }));
    }

    @Async
    @RequestMapping(produces = "application/json", value = "/getByProject", method = RequestMethod.GET)
    public CompletableFuture<CompletionStage<ResponseEntity<Result<List<ContactPersonDto>>>>> getContactPersonByProjectContainsIgnoreCase(@RequestParam String projectName) {
        return CompletableFuture.completedFuture(contactPersonServiceImpl.getContactPersonByProjectContainsIgnoreCase(projectName)
                .thenApply(contactPersons -> {
                    List<ContactPersonDto> contactPersonDtoList = contactPersons
                            .stream()
                            .map(mapper::toContactPersonDto)
                            .collect(Collectors.toList());
                    return Responses.ok(contactPersonDtoList);
                }).exceptionally(throwable -> {
                    LOGGER.error("Failed to get contact person = {}", projectName, throwable);
                    return Responses.internalServerError();
                }));
    }
}
