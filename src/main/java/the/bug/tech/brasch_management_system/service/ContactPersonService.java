package the.bug.tech.brasch_management_system.service;

import the.bug.tech.brasch_management_system.model.ContactPerson;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface ContactPersonService {

    CompletionStage<ContactPerson> insertContactPerson(ContactPerson contactPerson);

    CompletionStage<ContactPerson> getContactPersonById(String contactPersonId);

    CompletionStage<List<ContactPerson>> getAllContactPerson();

    CompletionStage<ContactPerson> updateContactPerson(ContactPerson contactPerson);

    CompletionStage<Void> deleteContactPerson(ContactPerson contactPerson);

    CompletionStage<ContactPerson> getContactPersonByNameContainsIgnoreCase(String contactPersonName);

    CompletionStage<List<ContactPerson>> getContactPersonByCompanyContainsIgnoreCase(String companyName);

    CompletionStage<List<ContactPerson>> getContactPersonByProjectContainsIgnoreCase(String projectName);
}
