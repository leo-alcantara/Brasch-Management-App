package the.bug.tech.brasch_management_system.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import the.bug.tech.brasch_management_system.model.Company;
import the.bug.tech.brasch_management_system.model.ContactPerson;
import the.bug.tech.brasch_management_system.model.Project;
import the.bug.tech.brasch_management_system.model.ProjectManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ProjectDto {

    private String projectName;
    private String projectDescription;
    private String projectLocal;
    private Project.Status status;
    private LocalDate projectedStartDate;
    private LocalDate projectedConclusionDate;
    private Company company;
    private ProjectManager projectManager;
    private List<ContactPerson> contactPerson;

    @JsonCreator
    public ProjectDto(@JsonProperty("projectName") String projectName,
                      @JsonProperty("projectDescription") String projectDescription,
                      @JsonProperty("projectLocal") String projectLocal,
                      @JsonProperty("status") Project.Status status,
                      @JsonProperty("projectedStartDate") LocalDate projectedStartDate,
                      @JsonProperty("projectedConclusionDate") LocalDate projectedConclusionDate,
                      @JsonProperty("company") Company company,
                      @JsonProperty("projectManager") ProjectManager projectManager,
                      @JsonProperty("contactPerson") List<ContactPerson> contactPerson) {
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

    public Project.Status getStatus() {
        return status;
    }

    public void setStatus(Project.Status status) {
        this.status = status;
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
    public String toString() {
        return "ProjectDto{" +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", projectLocal='" + projectLocal + '\'' +
                ", status=" + status +
                ", projectedStartDate=" + projectedStartDate +
                ", projectedConclusionDate=" + projectedConclusionDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDto that = (ProjectDto) o;
        return Objects.equals(getProjectName(), that.getProjectName()) && Objects.equals(getProjectDescription(), that.getProjectDescription()) && Objects.equals(getProjectLocal(), that.getProjectLocal()) && getStatus() == that.getStatus() && Objects.equals(getProjectedStartDate(), that.getProjectedStartDate()) && Objects.equals(getProjectedConclusionDate(), that.getProjectedConclusionDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectName(), getProjectDescription(), getProjectLocal(), getStatus(), getProjectedStartDate(), getProjectedConclusionDate());
    }
}
