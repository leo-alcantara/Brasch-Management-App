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

    private final ContactPersonRepository CONTACT_PERSON_REPOSITORY;
    private final Executor EXECUTOR;
    @PersistenceContext
    private final EntityManager ENTITY_MANAGER;

    public ContactPersonRepositoryAsync(ContactPersonRepository CONTACT_PERSON_REPOSITORY, Executor EXECUTOR, EntityManager ENTITY_MANAGER) {
        this.CONTACT_PERSON_REPOSITORY = CONTACT_PERSON_REPOSITORY;
        this.EXECUTOR = EXECUTOR;
        this.ENTITY_MANAGER = ENTITY_MANAGER;
    }

    public CompletionStage<ContactPerson> insertContactPerson(ContactPerson contactPerson) {
        return CompletableFuture.supplyAsync(() -> CONTACT_PERSON_REPOSITORY.save(contactPerson), EXECUTOR);
    }

    public CompletionStage<ContactPerson> getContactPersonById(String contactPersonId) {
        return CompletableFuture.supplyAsync(() -> CONTACT_PERSON_REPOSITORY.getById(contactPersonId), EXECUTOR);
    }

    public CompletionStage<List<ContactPerson>> getAllContactPerson() {
        return CompletableFuture.supplyAsync(() -> CONTACT_PERSON_REPOSITORY.findAll(), EXECUTOR);
    }

    public CompletionStage<Void> deleteContactPerson(ContactPerson contactPerson) {
        return CompletableFuture.supplyAsync(() -> {
            CONTACT_PERSON_REPOSITORY.delete(contactPerson);
            return null;
        }, EXECUTOR);
    }

    public CompletionStage<ContactPerson> updateContactPerson(ContactPerson contactPerson) {
        return CompletableFuture.supplyAsync(() -> ENTITY_MANAGER.merge(contactPerson), EXECUTOR);
    }

    public CompletionStage<ContactPerson> getContactPersonByNameContainsIgnoreCase(String contactPersonName) {
        return CompletableFuture.supplyAsync(() -> CONTACT_PERSON_REPOSITORY.getContactPersonByNameContainsIgnoreCase(contactPersonName), EXECUTOR);
    }

    public CompletionStage<List<ContactPerson>> getContactPersonByCompanyContainsIgnoreCase(String companyName) {
        return CompletableFuture.supplyAsync(() -> CONTACT_PERSON_REPOSITORY.getContactPersonByCompanyContainsIgnoreCase(companyName), EXECUTOR);
    }

    public CompletionStage<List<ContactPerson>> getContactPersonByProjectContainsIgnoreCase(String projectName) {
        return CompletableFuture.supplyAsync(() -> CONTACT_PERSON_REPOSITORY.getContactPersonByProjectContainsIgnoreCase(projectName), EXECUTOR);
    }
}
