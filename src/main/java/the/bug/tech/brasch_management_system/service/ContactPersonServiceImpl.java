package the.bug.tech.brasch_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.model.ContactPerson;
import the.bug.tech.brasch_management_system.repository.ContactPersonRepositoryAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
public class ContactPersonServiceImpl implements ContactPersonService {

    private ContactPersonRepositoryAsync CONTACT_PERSON_REPOSITORY_ASYNC;

    @Autowired
    public ContactPersonServiceImpl(ContactPersonRepositoryAsync CONTACT_PERSON_REPOSITORY_ASYNC) {
        this.CONTACT_PERSON_REPOSITORY_ASYNC = CONTACT_PERSON_REPOSITORY_ASYNC;
    }

    @Override
    @Transactional
    public CompletionStage<ContactPerson> insertContactPerson(ContactPerson contactPerson) {
        return CONTACT_PERSON_REPOSITORY_ASYNC.insertContactPerson(contactPerson);
    }

    @Override
    public CompletionStage<ContactPerson> getContactPersonById(String contactPersonId) {
        return CONTACT_PERSON_REPOSITORY_ASYNC.getContactPersonById(contactPersonId);
    }

    @Override
    public CompletionStage<List<ContactPerson>> getAllContactPerson() {
        return CONTACT_PERSON_REPOSITORY_ASYNC.getAllContactPerson();
    }

    @Override
    @Transactional
    public CompletionStage<ContactPerson> updateContactPerson(ContactPerson contactPerson) {
        return CONTACT_PERSON_REPOSITORY_ASYNC.updateContactPerson(contactPerson);
    }

    @Override
    @Transactional
    public CompletionStage<Void> deleteContactPerson(ContactPerson contactPerson) {
        return CONTACT_PERSON_REPOSITORY_ASYNC.deleteContactPerson(contactPerson);
    }

    @Override
    public CompletionStage<ContactPerson> getContactPersonByNameContainsIgnoreCase(String contactPersonName) {
        return CONTACT_PERSON_REPOSITORY_ASYNC.getContactPersonByNameContainsIgnoreCase(contactPersonName);
    }

    @Override
    public CompletionStage<List<ContactPerson>> getContactPersonByCompanyContainsIgnoreCase(String companyName) {
        return CONTACT_PERSON_REPOSITORY_ASYNC.getContactPersonByCompanyContainsIgnoreCase(companyName);
    }

    @Override
    public CompletionStage<List<ContactPerson>> getContactPersonByProjectContainsIgnoreCase(String projectName) {
        return CONTACT_PERSON_REPOSITORY_ASYNC.getContactPersonByProjectContainsIgnoreCase(projectName);
    }
}
