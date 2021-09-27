package the.bug.tech.brasch_management_system.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
    private String projectName;
    private String projectDescription;
    private String projectLocal;
    private Status status;
    private LocalDate projectedStartDate;
    private LocalDate projectedConclusionDate;

    @ManyToOne(cascade={CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(cascade={CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "project_manager_id")
    private ProjectManager projectManager;

    @ManyToMany(cascade = {CascadeType.DETACH,
    CascadeType.MERGE,
    CascadeType.PERSIST,
    CascadeType.REFRESH},
    fetch = FetchType.LAZY)
    @JoinTable(name = "projects_and_contact_person"
            , joinColumns = @JoinColumn(name = "project_id")
            , inverseJoinColumns = @JoinColumn(name = "contact_person_id"))
    private List<ContactPerson> contactPerson;

    public Project() {

    }

    public Project(String projectName, String projectDescription, String projectLocal,
                   Status status, LocalDate projectedStartDate, LocalDate projectedConclusionDate,
                   Company company, ProjectManager projectManager, List<ContactPerson> contactPerson) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectLocal = projectLocal;
        this.status = status;
        this.projectedStartDate = projectedStartDate;
        this.projectedConclusionDate = projectedConclusionDate;
        this.company = company;
        this.projectManager = projectManager;
        this.contactPerson = contactPerson;
    }

    public Project(String projectName, String projectDescription, Status status, Company company,
                   List<ContactPerson> contactPerson) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.status = status;
        this.company = company;
        this.contactPerson = contactPerson;
    }

    //Convenience Methods (ContactPerson, ProjectManager)

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
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

    public List<ContactPerson> getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(List<ContactPerson> contactPerson) {
        this.contactPerson = contactPerson;
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

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectLocal='" + projectLocal + '\'' +
                ", status=" + status +
                ", projectedStartDate=" + projectedStartDate +
                ", projectedConclusionDate=" + projectedConclusionDate +
                ", company=" + company +
                ", projectManager=" + projectManager +
                ", contactPerson=" + contactPerson +
                '}';
    }
}
