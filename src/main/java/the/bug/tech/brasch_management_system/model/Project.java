package the.bug.tech.brasch_management_system.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.vavr.control.Try;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    private String projectName;
    private String projectDescription;
    private String projectLocal;
    private Status status;
    private LocalDate projectedStartDate;
    private LocalDate projectedConclusionDate;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "project_manager_id")
    private ProjectManager projectManager;

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "projects_and_contact_person"
            , joinColumns = @JoinColumn(name = "project_id")
            , inverseJoinColumns = @JoinColumn(name = "contact_person_id"))
    private List<ContactPerson> contactPersonList;

    public Project() {

    }

    public Project(String projectName, String projectDescription, String projectLocal,
                   Status status, LocalDate projectedStartDate, LocalDate projectedConclusionDate,
                   Company company, ProjectManager projectManager, List<ContactPerson> contactPersonList) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectLocal = projectLocal;
        this.status = status;
        this.projectedStartDate = projectedStartDate;
        this.projectedConclusionDate = projectedConclusionDate;
        this.company = company;
        this.projectManager = projectManager;
        this.contactPersonList = contactPersonList;
    }

    public Project(String projectName, String projectDescription, Status status, Company company,
                   List<ContactPerson> contactPersonList) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.status = status;
        this.company = company;
        this.contactPersonList = contactPersonList;
    }

    //Convenience Methods (ContactPerson, ProjectManager)
    public void addContactPerson(ContactPerson contactPerson) {
        contactPersonList.add(contactPerson);
        contactPerson.getProjectList().add(this);
    }

    public void removeContactPerson(ContactPerson contactPerson) {
        contactPersonList.remove(contactPerson);
        contactPerson.getProjectList().remove(this);
    }

    public void addProjectManager(ProjectManager projectManager) {
        setProjectManager(projectManager);
    }

    public void removeProjectManager(ProjectManager projectManager) {
        setProjectManager(null);
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectLocal() {
        return projectLocal;
    }

    public void setProjectLocal(String projectLocal) {
        this.projectLocal = projectLocal;
    }

    public Status getProjectStatus() {
        return status;
    }

    public void setProjectStatus(Status projectStatus) {
        this.status = projectStatus;
    }

    public LocalDate getProjectedStartDate() {
        return projectedStartDate;
    }

    public void setProjectedStartDate(LocalDate projectedStartDate) {
        this.projectedStartDate = projectedStartDate;
    }

    public LocalDate getProjectedConclusionDate() {
        return projectedConclusionDate;
    }

    public void setProjectedConclusionDate(LocalDate projectedConclusionDate) {
        this.projectedConclusionDate = projectedConclusionDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public List<ContactPerson> getContactPersonList() {
        return contactPersonList;
    }

    public void setContactPersonList(List<ContactPerson> contactPerson) {
        this.contactPersonList = contactPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(getProjectName(), project.getProjectName()) && Objects.equals(getProjectDescription(), project.getProjectDescription()) && Objects.equals(getProjectLocal(), project.getProjectLocal()) && Objects.equals(getCompany(), project.getCompany());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectName(), getProjectDescription(), getProjectLocal(), getCompany());
    }


    public enum Status {

        HAS_NOT_STARTED("Has not started"),
        IN_PROGRESS("In Progress"),
        COMPLETED("Completed"),
        CANCELLED("Cancelled");

        public final String value;

        Status(String value) {
            this.value = value;
        }

        @JsonCreator
        public static Status fromValue(String value) {
            return Try.of(() -> Arrays.stream(values())
                            .filter(v -> v.value.equals(value))
                            .findFirst().get())
                    .getOrElseThrow(() -> new IllegalArgumentException("Failed to parse attribute type"));
        }

        @JsonValue
        public String toJson() {
            return value;
        }
    }
}
