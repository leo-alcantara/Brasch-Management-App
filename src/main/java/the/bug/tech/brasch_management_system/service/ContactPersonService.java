package the.bug.tech.brasch_management_system.service;

import io.vavr.control.Option;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.model.ContactPerson;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface ContactPersonService {

    CompletionStage<ContactPerson> insertContactPerson(ContactPerson contactPerson);

    CompletionStage<ContactPerson> getContactPersonById(String contactPersonId);

    CompletionStage<List<ContactPerson>> getAllContactPerson();

    CompletionStage<ContactPerson> updateContactPerson(ContactPerson contactPerson);

    CompletionStage<Option<Void>> deleteContactPerson(ContactPerson contactPerson);

    CompletionStage<Option<ContactPerson>> getContactPersonByNameContainsIgnoreCase(String contactPersonName);

    CompletionStage<List<ContactPerson>> getContactPersonByCompanyContainsIgnoreCase(String companyName);

    CompletionStage<List<ContactPerson>> getContactPersonByProjectContainsIgnoreCase(String projectName);
}
