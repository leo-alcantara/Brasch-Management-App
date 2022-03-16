package the.bug.tech.brasch_management_system.repository;

import org.springframework.stereotype.Repository;
import the.bug.tech.brasch_management_system.model.ContactPerson;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;

@Repository
public class ContactPersonRepositoryAsync {

    private final ContactPersonRepository contactPersonRepository;
    private final Executor executor;

    public ContactPersonRepositoryAsync(ContactPersonRepository contactPersonRepository, Executor executor) {
        this.contactPersonRepository = contactPersonRepository;
        this.executor = executor;
    }

    public CompletionStage<ContactPerson> insertContactPerson(ContactPerson contactPerson) {
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.create(contactPerson), executor);
    }

    public CompletionStage<ContactPerson> getContactPersonById(Integer contactPersonId) {
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.findById(contactPersonId), executor);
    }

    public CompletionStage<List<ContactPerson>> getAllContactPerson() {
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.findAll(), executor);
    }

    public CompletionStage<Void> deleteContactPerson(ContactPerson contactPerson) {
        return CompletableFuture.runAsync(() ->
            contactPersonRepository.delete(contactPerson), executor);
    }

    public CompletionStage<ContactPerson> updateContactPerson(ContactPerson contactPerson) {
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.update(contactPerson), executor);
    }

    public CompletionStage<List<ContactPerson>> getContactPersonByNameContainsIgnoreCase(String contactPersonName) {
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.getContactPersonByNameContainsIgnoreCase(contactPersonName), executor);
    }

    public CompletionStage<List<ContactPerson>> getContactPersonByCompanyContainsIgnoreCase(String companyName) {
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.getContactPersonByCompanyContainsIgnoreCase(companyName), executor);
    }

    public CompletionStage<List<ContactPerson>> getContactPersonByProjectContainsIgnoreCase(String projectName) {
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.getContactPersonByProjectContainsIgnoreCase(projectName), executor);
    }
}
