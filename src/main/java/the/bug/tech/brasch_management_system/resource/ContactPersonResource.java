package the.bug.tech.brasch_management_system.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.model.ContactPerson;
import the.bug.tech.brasch_management_system.model.dto.ContactPersonDto;
import the.bug.tech.brasch_management_system.service.ContactPersonServiceImpl;
import the.bug.tech.brasch_management_system.service.EntityDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/contactPerson")
public class ContactPersonResource {

    private final ContactPersonServiceImpl contactPersonServiceImpl;
    private final EntityDtoMapper mapper;

    @Autowired
    public ContactPersonResource(ContactPersonServiceImpl contactPersonServiceImpl, EntityDtoMapper mapper) {
        this.contactPersonServiceImpl = contactPersonServiceImpl;
        this.mapper = mapper;
    }


    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    public ResponseEntity<ContactPersonDto> insertContactPerson(@RequestBody ContactPersonDto contactPersonDto) {
        ContactPerson insertedContactPerson = contactPersonServiceImpl.insertContactPerson(mapper.toContactPerson(contactPersonDto));
        ContactPersonDto insertedContactPersonDto = mapper.toContactPersonDto(insertedContactPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedContactPersonDto);
    }


    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<ContactPersonDto> getContactPersonById(@RequestParam Integer contactPersonId) {
        ContactPerson contactPersonFoundById = contactPersonServiceImpl.getContactPersonById(contactPersonId);
        ContactPersonDto contactPersonFoundByIdDto = mapper.toContactPersonDto(contactPersonFoundById);
        return ResponseEntity.ok(contactPersonFoundByIdDto);
    }

    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<ContactPersonDto>> getAllContactPerson() {
        List<ContactPerson> contactPeople = contactPersonServiceImpl.getAllContactPerson();
        List<ContactPersonDto> contactPeopleDto = contactPeople
                .stream()
                .map(mapper::toContactPersonDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contactPeopleDto);
    }

    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<ContactPersonDto> updateContactPerson(Integer contactPersonToUpdateId,
                                                                @RequestBody ContactPersonDto updateContactPersonDto) {
        ContactPerson convertedContactPerson = contactPersonServiceImpl.updateContactPerson(contactPersonToUpdateId, mapper.toContactPerson(updateContactPersonDto));
        ContactPersonDto convertedContactPersonDto = mapper.toContactPersonDto(convertedContactPerson);
        return ResponseEntity.ok().body(convertedContactPersonDto);
    }

    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteContactPerson(@RequestBody Integer contactPersonId) {
        contactPersonServiceImpl.deleteContactPerson(contactPersonId);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(produces = "application/json", value = "/getByName", method = RequestMethod.GET)
    public ResponseEntity<List<ContactPersonDto>> getContactPersonByNameContainsIgnoreCase(@RequestParam String contactPersonName) {
        List<ContactPerson> peopleFoundByName = contactPersonServiceImpl.getContactPersonByNameContainsIgnoreCase(contactPersonName);
        List<ContactPersonDto> peopleFoundByNameDto = peopleFoundByName
                .stream()
                .map(mapper::toContactPersonDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(peopleFoundByNameDto);
    }


    @RequestMapping(produces = "application/json", value = "/getByCompany", method = RequestMethod.GET)
    public ResponseEntity<List<ContactPersonDto>> getContactPersonByCompanyContainsIgnoreCase(@RequestParam String companyName) {
        List<ContactPerson> peopleFoundByCompany = contactPersonServiceImpl.getContactPersonByNameContainsIgnoreCase(companyName);
        List<ContactPersonDto> peopleFoundByCompanyDto = peopleFoundByCompany
                .stream()
                .map(mapper::toContactPersonDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(peopleFoundByCompanyDto);
    }


    @RequestMapping(produces = "application/json", value = "/getByProject", method = RequestMethod.GET)
    public ResponseEntity<List<ContactPersonDto>> getContactPersonByProjectContainsIgnoreCase(@RequestParam String projectName) {
        List<ContactPerson> peopleFoundByProject = contactPersonServiceImpl.getContactPersonByProjectContainsIgnoreCase(projectName);
        List<ContactPersonDto> peopleFoundByProjectDto = peopleFoundByProject
                .stream()
                .map(mapper::toContactPersonDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(peopleFoundByProjectDto);
    }
}
