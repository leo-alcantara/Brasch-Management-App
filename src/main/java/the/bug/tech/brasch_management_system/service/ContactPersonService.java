package the.bug.tech.brasch_management_system.service;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.repository.ContactPersonRepository;
import the.bug.tech.brasch_management_system.model.ContactPerson;
import the.bug.tech.brasch_management_system.repository.ContactPersonRepositoryAsync;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

@Service
public class ContactPersonService {

    private ContactPersonRepository contactPersonRepository;
    private ContactPersonRepositoryAsync contactPersonRepositoryAsync;

    @Autowired
    public ContactPersonService(ContactPersonRepository contactPersonRepository, ContactPersonRepositoryAsync contactPersonRepositoryAsync) {
        this.contactPersonRepository = contactPersonRepository;
        this.contactPersonRepositoryAsync = contactPersonRepositoryAsync;
    }


    public ContactPerson createContactPerson(ContactPerson contactPerson){
        return contactPersonRepository.save(contactPerson);
    }


    public ContactPerson findById(String contactPersonId) {
        Optional<ContactPerson> foundById = contactPersonRepository.findById(contactPersonId);
        return foundById.orElseThrow(IllegalArgumentException::new);
    }


    public List<ContactPerson> findAll() {
        return contactPersonRepository.findAll();
    }


    //Review this method
    @Transactional
    public ContactPerson update(ContactPerson contactPerson){
        ContactPerson original = contactPersonRepository.findById(contactPerson.getContactPersonId()).get();

        original.setContactPersonPerson(contactPerson.getContactPersonPerson());
        original.setCompany(contactPerson.getCompany());
        original.setProjectList(contactPerson.getProjectList());
        return original;
    }


    public void delete(String contactPersonId){
        contactPersonRepository.deleteById(contactPersonId);
    }

    public CompletionStage<Option<ContactPerson>> findContactPersonByNameContainsIgnoreCase(String contactPersonName){
        return contactPersonRepositoryAsync.getContactPersonByNameContainsIgnoreCase(contactPersonName);
    }

    public CompletionStage<List<ContactPerson>> findContactPersonByCompanyContainsIgnoreCase(String companyName){
        return contactPersonRepositoryAsync.getContactPersonByCompanyContainsIgnoreCase(companyName);
    }

    public CompletionStage<List<ContactPerson>> findContactPersonByProjectContainsIgnoreCase(String projectName){
        return contactPersonRepositoryAsync.getContactPersonByProjectContainsIgnoreCase(projectName);
    }
}
