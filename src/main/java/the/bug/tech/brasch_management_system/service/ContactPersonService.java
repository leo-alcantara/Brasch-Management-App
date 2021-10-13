package the.bug.tech.brasch_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.data.repository.ContactPersonRepository;
import the.bug.tech.brasch_management_system.model.ContactPerson;

import java.util.List;
import java.util.Optional;

@Service
public class ContactPersonService {

    private ContactPersonRepository contactPersonRepository;

    @Autowired
    public ContactPersonService(ContactPersonRepository contactPersonRepository) {
        this.contactPersonRepository = contactPersonRepository;
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

    @Transactional
    public ContactPerson update(ContactPerson contactPerson){
        ContactPerson original= contactPersonRepository.findById(contactPerson.getContactPersonId()).get();
        original.setContactPersonPerson(contactPerson.getContactPersonPerson());
        original.setCompany(contactPerson.getCompany());
        original.setProjectList(contactPerson.getProjectList());
        return original;
    }

    public void delete(String contactPersonId){
        contactPersonRepository.deleteById(contactPersonId);
    }

    public ContactPerson findContactPersonByNameContainsIgnoreCase(String contactPersonName){
        return contactPersonRepository.findContactPersonByNameContainsIgnoreCase(contactPersonName);
    }

    public List<ContactPerson> findContactPersonByCompanyContainsIgnoreCase(String companyName){
        return contactPersonRepository.findContactPersonByCompanyContainsIgnoreCase(companyName);
    }

    public List<ContactPerson> findContactPersonByProjectContainsIgnoreCase(String projectName){
        return contactPersonRepository.findContactPersonByProjectContainsIgnoreCase(projectName);
    }
}
