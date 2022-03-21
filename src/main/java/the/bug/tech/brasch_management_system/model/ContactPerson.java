package the.bug.tech.brasch_management_system.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class ContactPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contactPersonId;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person contactPersonPerson;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "projects_and_contact_person",
            joinColumns = @JoinColumn(name = "contact_person_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projectList;

    public ContactPerson() {
    }

    public ContactPerson(Person contactPersonPerson, Company company, List<Project> projectList) {
        this.contactPersonPerson = contactPersonPerson;
        this.company = company;
        this.projectList = projectList;
    }

    public ContactPerson(Person contactPersonPerson, Company company) {
        this.contactPersonPerson = contactPersonPerson;
        this.company = company;
    }

    public Integer getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(Integer contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public Person getContactPersonPerson() {
        return contactPersonPerson;
    }

    public void setContactPersonPerson(Person contactPersonPerson) {
        this.contactPersonPerson = contactPersonPerson;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactPerson that = (ContactPerson) o;
        return Objects.equals(getContactPersonPerson(), that.getContactPersonPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContactPersonPerson());
    }


}
