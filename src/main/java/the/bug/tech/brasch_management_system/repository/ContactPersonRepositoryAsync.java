package the.bug.tech.brasch_management_system.repository;

import io.vavr.control.Option;
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
    @PersistenceContext
    private final EntityManager entityManager;

    public ContactPersonRepositoryAsync(ContactPersonRepository contactPersonRepository, Executor executor, EntityManager entityManager) {
        this.contactPersonRepository = contactPersonRepository;
        this.executor = executor;
        this.entityManager = entityManager;
    }

    public CompletionStage<ContactPerson> insertContactPerson(ContactPerson contactPerson) {
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.save(contactPerson), executor);
    }

    public CompletionStage<ContactPerson> getContactPersonById(String contactPersonId) {
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.getById(contactPersonId), executor);
    }

    public CompletionStage<List<ContactPerson>> getAllContactPerson() {
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.findAll(), executor);
    }

    public CompletionStage<Void> deleteContactPerson(ContactPerson contactPerson) {
        return CompletableFuture.supplyAsync(() -> {
            contactPersonRepository.delete(contactPerson);
            return null;
        }, executor);
    }

    public CompletionStage<ContactPerson> updateContactPerson(ContactPerson contactPerson) {
        return CompletableFuture.supplyAsync(() -> entityManager.merge(contactPerson), executor);
    }

    public CompletionStage<ContactPerson> getContactPersonByNameContainsIgnoreCase(String contactPersonName) {
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.getContactPersonByNameContainsIgnoreCase(contactPersonName), executor);
    }

    public CompletionStage<List<ContactPerson>> getContactPersonByCompanyContainsIgnoreCase(String companyName) {
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.getContactPersonByCompanyContainsIgnoreCase(companyName), executor);
    }

    public CompletionStage<List<ContactPerson>> getContactPersonByProjectContainsIgnoreCase(String projectName) {
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.getContactPersonByProjectContainsIgnoreCase(projectName), executor);
    }
}
