package the.bug.tech.brasch_management_system.repository;

import the.bug.tech.brasch_management_system.model.ContactPerson;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ContactPersonRepositorySql implements ContactPersonRepository{

    @PersistenceContext
    private EntityManager entityManager;

    private static final String FIND_CONTACT_PERSON_BY_NAME =
            "SELECT cp FROM ContactPerson cp JOIN FETCH cp.contactPersonPerson AS p WHERE UPPER(CONCAT(p.firstName, p.lastName)) LIKE UPPER(CONCAT('%', :contactPersonName, '%') )";

    private static final String FIND_CONTACT_PERSON_BY_COMPANY =
            "SELECT cp FROM ContactPerson cp JOIN FETCH cp.company AS c WHERE UPPER(c.companyName) LIKE UPPER(CONCAT('%', :companyName, '%'))";

    private static final String  FIND_CONTACT_PERSON_BT_PROJECT =
            "SELECT cp FROM ContactPerson cp JOIN FETCH cp.projectList AS p WHERE UPPER(p.projectName) LIKE UPPER(CONCAT('%', :projectName, '%'))";

    private static final String  FIN_ALL_CONTACT_PEOPLE =
            "SELECT cp FROM ContactPerson cp";

    @Override
    public List<ContactPerson> getContactPersonByNameContainsIgnoreCase(String contactPersonName) {
        if(contactPersonName.isEmpty()) {
            return findAll();
        }
        return entityManager.createQuery(FIND_CONTACT_PERSON_BY_NAME, ContactPerson.class)
                .setParameter("contactPersonName", contactPersonName)
                .getResultList();
    }

    @Override
    public List<ContactPerson> getContactPersonByCompanyContainsIgnoreCase(String companyName) {
        if(companyName.isEmpty()) {
            return findAll();
        }
        return entityManager.createQuery(FIND_CONTACT_PERSON_BY_COMPANY, ContactPerson.class)
                .setParameter("companyName", companyName)
                .getResultList();
    }

    @Override
    public List<ContactPerson> getContactPersonByProjectContainsIgnoreCase(String projectName) {
        if(projectName.isEmpty()) {
            return findAll();
        }
        return entityManager.createQuery(FIND_CONTACT_PERSON_BT_PROJECT, ContactPerson.class)
                .setParameter("projectName", projectName)
                .getResultList();
    }

    @Override
    public ContactPerson create(ContactPerson contactPerson) {
        entityManager.persist(contactPerson);
        return contactPerson;
    }

    @Override
    public ContactPerson delete(ContactPerson contactPerson) {
        entityManager.remove(contactPerson);
        return contactPerson;
    }

    @Override
    public List<ContactPerson> findAll() {
        return entityManager.createQuery(FIN_ALL_CONTACT_PEOPLE, ContactPerson.class)
                .getResultList();
    }

    @Override
    public ContactPerson findById(Integer contactPersonId) {
        return entityManager.find(ContactPerson.class, contactPersonId);
    }

    @Override
    public ContactPerson update(ContactPerson contactPerson) {
        return entityManager.merge(contactPerson);
    }

    @Override
    public void clear() {
        entityManager.clear();
    }
}
