package the.bug.tech.brasch_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.exceptions.ResourceNotFoundException;
import the.bug.tech.brasch_management_system.model.ContactPerson;
import the.bug.tech.brasch_management_system.repository.ContactPersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactPersonServiceImpl implements ContactPersonService {

    private final ContactPersonRepository contactPersonRepository;

    @Autowired
    public ContactPersonServiceImpl(ContactPersonRepository contactPersonRepository) {
        this.contactPersonRepository = contactPersonRepository;
    }

    @Transactional
    public ContactPerson insertContactPerson(ContactPerson contactPerson) {
        contactPersonRepository.save(contactPerson);
        return contactPerson;
    }

    @Transactional
    public ContactPerson getContactPersonById(Integer contactPersonId) {
        Optional<ContactPerson> foundContactPerson = contactPersonRepository.findById(contactPersonId);

        if (foundContactPerson.isPresent()) {
            return foundContactPerson.get();
        } else {
            throw new ResourceNotFoundException("Could not find Contact Person with id " + contactPersonId);
        }
    }

    @Transactional
    public List<ContactPerson> getAllContactPerson() {
        return contactPersonRepository.findAll();
    }

    @Transactional
    public ContactPerson updateContactPerson(Integer contactPersonId, ContactPerson contactPerson) {
        Optional<ContactPerson> original = contactPersonRepository.findById(contactPersonId);

        if(original.isPresent()) {
            original.get().setContactPersonPerson(contactPerson.getContactPersonPerson());
            original.get().setCompany(contactPerson.getCompany());
            original.get().setProjectList(contactPerson.getProjectList());
            return original.get();
        } else {
            throw new ResourceNotFoundException("Could not update Contact Person with id " + contactPersonId);
        }
    }

    @Transactional
    public void deleteContactPerson(Integer contactPersonId) {
        contactPersonRepository.deleteById(contactPersonId);
    }

    @Transactional
    public List<ContactPerson> getContactPersonByNameContainsIgnoreCase(String contactPersonName) {
        return contactPersonRepository.getContactPersonByNameContainsIgnoreCase(contactPersonName);
    }

    @Transactional
    public List<ContactPerson> getContactPersonByCompanyContainsIgnoreCase(String companyName) {
        return contactPersonRepository.getContactPersonByCompanyContainsIgnoreCase(companyName);
    }

    @Transactional
    public List<ContactPerson> getContactPersonByProjectContainsIgnoreCase(String projectName) {
        return contactPersonRepository.getContactPersonByProjectContainsIgnoreCase(projectName);
    }
}
