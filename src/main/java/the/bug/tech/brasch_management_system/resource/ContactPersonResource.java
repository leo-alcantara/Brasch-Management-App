package the.bug.tech.brasch_management_system.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.dto.Result;
import the.bug.tech.brasch_management_system.model.ContactPerson;
import the.bug.tech.brasch_management_system.model.dto.ContactPersonDto;
import the.bug.tech.brasch_management_system.service.ContactPersonServiceImpl;
import the.bug.tech.brasch_management_system.service.EntityDtoMapper;
import the.bug.tech.brasch_management_system.util.Responses;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/contactPerson")
public class ContactPersonResource {

    private static final Logger logger = LoggerFactory.getLogger(ContactPerson.class);

    private final ContactPersonServiceImpl contactPersonServiceImpl;
    private final EntityDtoMapper converter;

    @Autowired
    public ContactPersonResource(ContactPersonServiceImpl contactPersonServiceImpl, EntityDtoMapper converter) {
        this.contactPersonServiceImpl = contactPersonServiceImpl;
        this.converter = converter;
    }

    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    //@ApiOperation(value = "Insert contact person instance", response = ContactPersonDto.class)
    public CompletionStage<ResponseEntity<Result<String>>> create(@RequestBody ContactPersonDto contactPersonDto) {
        return contactPersonServiceImpl.insertContactPerson(converter.toContactPerson(contactPersonDto))
                .thenApply(project -> Responses.ok(""))
                .exceptionally(throwable -> {
                    logger.error("Failed to insert contact person = {}", contactPersonDto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.POST)
    //@ApiOperation(value = "Gets contact person instance by id", response = ContactPersonDto.class)
    public CompletionStage<ResponseEntity<Result<ContactPersonDto>>> getContactPersonById(@RequestParam String contactPersonId) {
        return contactPersonServiceImpl.getContactPersonById(contactPersonId)
                .thenApply(contactPerson -> {
                    ContactPersonDto contactPersonDto = converter.toContactPersonDto(contactPerson);
                    return Responses.ok(contactPersonDto);
                }).exceptionally(throwable -> {
                    logger.error("Failed to get contact person = {}", contactPersonId, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets all contact person instances", response = ContactPersonDto.class)
    public CompletionStage<ResponseEntity<Result<List<ContactPersonDto>>>> getAllContactPerson() {
        return contactPersonServiceImpl.getAllContactPerson().thenApply(contactPerson -> {
            List<ContactPersonDto> contactPersonDtoList = contactPerson.stream()
                    .map(converter::toContactPersonDto)
                    .collect(Collectors.toList());
            return Responses.ok(contactPersonDtoList);
        }).exceptionally(throwable -> {
            logger.error("Failed to get contact people = {}", throwable);
            return Responses.internalServerError();
        });
    }

    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    //@ApiOperation(value = "Updates contact person instance", response = ContactPersonDto.class)
    public CompletionStage<ResponseEntity<Result<String>>> updateContactPerson(@RequestBody ContactPersonDto contactPersonDto) {
        return contactPersonServiceImpl.updateContactPerson(converter.toContactPerson(contactPersonDto))
                .thenApply(company -> Responses.ok(""))
                .exceptionally(throwable -> {
                    logger.error("Failed to update contact person = {}", contactPersonDto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    //@ApiOperation(value = "Deletes contact person instance", response = ContactPersonDto.class)
    public CompletionStage<ResponseEntity<Result<Void>>> deleteContactPerson(@RequestBody ContactPersonDto contactPersonDto) {
        return contactPersonServiceImpl.deleteContactPerson(converter.toContactPerson(contactPersonDto))
                .thenApply(Responses::ok)
                .exceptionally(throwable -> {
                    logger.error("Failed to delete contact person = {}", contactPersonDto, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getByName", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets contact person instance by name", response = ContactPersonDto.class)
    public CompletionStage<ResponseEntity<Result<ContactPersonDto>>> getContactPersonByNameContainsIgnoreCase(@RequestParam String contactPersonName) {
        return contactPersonServiceImpl.getContactPersonByNameContainsIgnoreCase(contactPersonName)
                .thenApply(contactPerson -> {
                    ContactPersonDto contactPersonDto = converter.toContactPersonDto(contactPerson);
                    return Responses.ok(contactPersonDto);
                }).exceptionally(throwable -> {
                    logger.error("Failed to get company = {}", contactPersonName, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getByCompany", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets contact person instance by company", response = ContactPersonDto.class)
    public CompletionStage<ResponseEntity<Result<List<ContactPersonDto>>>> getContactPersonByCompanyContainsIgnoreCase(@RequestParam String companyName) {
        return contactPersonServiceImpl.getContactPersonByCompanyContainsIgnoreCase(companyName)
                .thenApply(contactPersonList -> {
                    List<ContactPersonDto> contactPersonDtoList = contactPersonList.stream()
                            .map(converter::toContactPersonDto)
                            .collect(Collectors.toList());
                    return Responses.ok(contactPersonDtoList);
                }).exceptionally(throwable -> {
                    logger.error("Failed to get project = {}", companyName, throwable);
                    return Responses.internalServerError();
                });
    }

    @RequestMapping(produces = "application/json", value = "/getByProject", method = RequestMethod.GET)
    //@ApiOperation(value = "Gets contact person instance by project", response = ContactPersonDto.class)
    public CompletionStage<ResponseEntity<Result<List<ContactPersonDto>>>> getContactPersonByProjectContainsIgnoreCase(@RequestParam String projectName) {
        return contactPersonServiceImpl.getContactPersonByProjectContainsIgnoreCase(projectName)
                .thenApply(contactPersonList -> {
                    List<ContactPersonDto> contactPersonDtoList = contactPersonList.stream()
                            .map(converter::toContactPersonDto)
                            .collect(Collectors.toList());
                    return Responses.ok(contactPersonDtoList);
                }).exceptionally(throwable -> {
                    logger.error("Failed to get project = {}", projectName, throwable);
                    return Responses.internalServerError();
                });
    }
}
