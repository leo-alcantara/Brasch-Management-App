package the.bug.tech.brasch_management_system.repository;

import io.vavr.control.Option;
import org.springframework.stereotype.Repository;
import the.bug.tech.brasch_management_system.model.ContactPerson;

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

    public CompletionStage<Option<ContactPerson>> getContactPersonByNameContainsIgnoreCase (String contactPersonName){
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.getContactPersonByNameContainsIgnoreCase(contactPersonName), executor);
    }

    public CompletionStage<List<ContactPerson>> getContactPersonByCompanyContainsIgnoreCase(String companyName){
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.getContactPersonByCompanyContainsIgnoreCase(companyName), executor);
    }

    public CompletionStage<List<ContactPerson>> getContactPersonByProjectContainsIgnoreCase (String projectName){
        return CompletableFuture.supplyAsync(() -> contactPersonRepository.getContactPersonByProjectContainsIgnoreCase(projectName), executor);
    }
}
