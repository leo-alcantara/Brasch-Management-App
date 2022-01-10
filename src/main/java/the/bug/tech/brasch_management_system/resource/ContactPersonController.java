package the.bug.tech.brasch_management_system.resource;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.model.ContactPerson;
import the.bug.tech.brasch_management_system.service.ContactPersonService;

import java.util.List;
import java.util.concurrent.CompletionStage;

@RestController
public class ContactPersonController {

    private final ContactPersonService contactPersonService;

    @Autowired
    public ContactPersonController(ContactPersonService contactPersonService) {
        this.contactPersonService = contactPersonService;
    }

    @PostMapping("/api/contact-person")
    public ResponseEntity<ContactPerson> create(@RequestBody ContactPerson contactPerson){
        ContactPerson saved= contactPersonService.createContactPerson(contactPerson);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/api/contact-person/{id}")
    public ResponseEntity<ContactPerson> findById(@PathVariable("id") String contactPersonId){
        ContactPerson foundById= contactPersonService.findById(contactPersonId);
        return ResponseEntity.ok(foundById);
    }

   /* @GetMapping("/api/contact-person")
    public ResponseEntity<List<ContactPerson>> findAll(){
        List<ContactPerson> allFound= contactPersonService.findAll();
        return ResponseEntity.ok(allFound);
    }*/

    @PutMapping("/api/contact-person/{id}")
    public ResponseEntity<ContactPerson> update(@PathVariable("id") String contactPersonId,
                                                @RequestBody ContactPerson contactPerson){
        if(contactPersonId.equals(contactPerson.getContactPersonId())){
            ContactPerson updated = contactPersonService.update(contactPerson);
            return ResponseEntity.ok().body(updated);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/contact-person/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String contactPersonId){
        contactPersonService.delete(contactPersonId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/contact-person/{contactPersonName}")
    public ResponseEntity<CompletionStage<Option<ContactPerson>>> findContactPersonByNameContainsIgnoreCase(@PathVariable("contactPersonName") String contactPersonName){
        CompletionStage<Option<ContactPerson>> foundContactPerson= contactPersonService.findContactPersonByNameContainsIgnoreCase(contactPersonName);
        return ResponseEntity.ok(foundContactPerson);
    }

    @GetMapping("/api/contact-person/{companyName}")
    public ResponseEntity<CompletionStage<List<ContactPerson>>> findContactPersonByCompanyContainsIgnoreCase(@PathVariable("companyName") String companyName){
        CompletionStage<List<ContactPerson>> foundPeople= contactPersonService.findContactPersonByCompanyContainsIgnoreCase(companyName);
        return ResponseEntity.ok(foundPeople);
    }

    @GetMapping("/api/contact-person/{projectName}")
    public ResponseEntity<CompletionStage<List<ContactPerson>>> findContactPersonByProjectContainsIgnoreCase(@PathVariable("projectName") String projectName){
        CompletionStage<List<ContactPerson>> foundPeople= contactPersonService.findContactPersonByProjectContainsIgnoreCase(projectName);
        return ResponseEntity.ok(foundPeople);
    }

}
