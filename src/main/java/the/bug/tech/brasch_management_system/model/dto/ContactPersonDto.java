package the.bug.tech.brasch_management_system.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import the.bug.tech.brasch_management_system.model.Company;
import the.bug.tech.brasch_management_system.model.Person;
import the.bug.tech.brasch_management_system.model.Project;

import java.util.List;
import java.util.Objects;

public class ContactPersonDto {

    private Person contactPersonPerson;
    private Company company;
    private List<Project> projectList;

    @JsonCreator
    public ContactPersonDto(@JsonProperty("contactPersonPerson") Person contactPersonPerson,
                            @JsonProperty("company") Company company,
                            @JsonProperty("projectList") List<Project> projectList) {
        this.contactPersonPerson = contactPersonPerson;
        this.company = company;
        this.projectList = projectList;
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
    public String toString() {
        return "ContactPersonDto{" +
                ", contactPersonPerson=" + contactPersonPerson +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactPersonDto that = (ContactPersonDto) o;
        return Objects.equals(getContactPersonPerson(), that.getContactPersonPerson()) && Objects.equals(getCompany(), that.getCompany()) && Objects.equals(getProjectList(), that.getProjectList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContactPersonPerson(), getCompany(), getProjectList());
    }
}
