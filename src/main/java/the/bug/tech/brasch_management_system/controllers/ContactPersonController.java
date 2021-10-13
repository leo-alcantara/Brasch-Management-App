package the.bug.tech.brasch_management_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.model.ContactPerson;
import the.bug.tech.brasch_management_system.service.ContactPersonService;

import java.util.List;

@RestController
public class ContactPersonController {

    private final ContactPersonService contactPersonService;

    @Autowired
    public ContactPersonController(ContactPersonService contactPersonService) {
        this.contactPersonService = contactPersonService;
    }

    @GetMapping("/api/contactperson")
    public ResponseEntity<ContactPerson> create(@RequestBody ContactPerson contactPerson){
        ContactPerson saved= contactPersonService.createContactPerson(contactPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/api/contactperson/{id}")
    public ResponseEntity<ContactPerson> findById(@PathVariable("id") String contactPersonId){
        ContactPerson foundById= contactPersonService.findById(contactPersonId);
        return ResponseEntity.ok(foundById);
    }

    @GetMapping("/api/contactperson")
    public ResponseEntity<List<ContactPerson>> findAll(){
        List<ContactPerson> allFound= contactPersonService.findAll();
        return ResponseEntity.ok(allFound);
    }

    @PutMapping("/api/companyperson/{id}")
    public ResponseEntity<ContactPerson> update(@PathVariable("id") String contactPersonId,
                                                @RequestBody ContactPerson contactPerson){
        if(contactPersonId.equals(contactPerson.getContactPersonId())){
            ContactPerson updated = contactPersonService.update(contactPerson);
            return ResponseEntity.ok().body(updated);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/contactperson/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String contactPersonId){
        contactPersonService.delete(contactPersonId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/contactperson/{contactPersonName}")
    public ResponseEntity<ContactPerson> findContactPersonByNameContainsIgnoreCase(@PathVariable("contactPersonName") String contactPersonName){
        ContactPerson foundContactPerson= contactPersonService.findContactPersonByNameContainsIgnoreCase(contactPersonName);
        return ResponseEntity.ok(foundContactPerson);
    }

    @GetMapping("/api/contactperson/{companyName}")
    public ResponseEntity<List<ContactPerson>> findContactPersonByCompanyContainsIgnoreCase(@PathVariable("companyName") String companyName){
        List<ContactPerson> foundPeople= contactPersonService.findContactPersonByCompanyContainsIgnoreCase(companyName);
        return ResponseEntity.ok(foundPeople);
    }

    @GetMapping("/api/contactperson/{projectName}")
    public ResponseEntity<List<ContactPerson>> findContactPersonByProjectContainsIgnoreCase(@PathVariable("projectName") String projectName){
        List<ContactPerson> foundPeople= contactPersonService.findContactPersonByProjectContainsIgnoreCase(projectName);
        return ResponseEntity.ok(foundPeople);
    }

}
