package the.bug.tech.brasch_management_system.service;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.model.ContactPerson;
import the.bug.tech.brasch_management_system.repository.ContactPersonRepositoryAsync;

import java.util.List;
import java.util.concurrent.CompletionStage;

@Service
public class ContactPersonServiceImpl implements ContactPersonService {

    private ContactPersonRepositoryAsync contactPersonRepositoryAsync;

    @Autowired
    public ContactPersonServiceImpl(ContactPersonRepositoryAsync contactPersonRepositoryAsync) {
        this.contactPersonRepositoryAsync = contactPersonRepositoryAsync;
    }

    @Override
    @Transactional
    public CompletionStage<ContactPerson> insertContactPerson(ContactPerson contactPerson) {
        return contactPersonRepositoryAsync.insertContactPerson(contactPerson);
    }

    @Override
    public CompletionStage<ContactPerson> getContactPersonById(String contactPersonId) {
        return contactPersonRepositoryAsync.getContactPersonById(contactPersonId);
    }

    @Override
    public CompletionStage<List<ContactPerson>> getAllContactPerson() {
        return contactPersonRepositoryAsync.getAllContactPerson();
    }

    @Override
    @Transactional
    public CompletionStage<ContactPerson> updateContactPerson(ContactPerson contactPerson) {
        return contactPersonRepositoryAsync.updateContactPerson(contactPerson);
    }

    @Override
    @Transactional
    public CompletionStage<Option<Void>> deleteContactPerson(ContactPerson contactPerson) {
        return contactPersonRepositoryAsync.deleteContactPerson(contactPerson);
    }

    @Override
    public CompletionStage<Option<ContactPerson>> getContactPersonByNameContainsIgnoreCase(String contactPersonName) {
        return contactPersonRepositoryAsync.getContactPersonByNameContainsIgnoreCase(contactPersonName);
    }

    @Override
    public CompletionStage<List<ContactPerson>> getContactPersonByCompanyContainsIgnoreCase(String companyName) {
        return contactPersonRepositoryAsync.getContactPersonByCompanyContainsIgnoreCase(companyName);
    }

    @Override
    public CompletionStage<List<ContactPerson>> getContactPersonByProjectContainsIgnoreCase(String projectName) {
        return contactPersonRepositoryAsync.getContactPersonByProjectContainsIgnoreCase(projectName);
    }
}
