package the.bug.tech.brasch_management_system.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import the.bug.tech.brasch_management_system.model.Person;
import the.bug.tech.brasch_management_system.model.Project;

import java.util.List;
import java.util.Objects;

public class ProjectManagerDto {

    private Person projectManagerPerson;
    private List<Project> projectList;

    @JsonCreator
    public ProjectManagerDto(@JsonProperty("projectManagerPerson") Person projectManagerPerson,
                             @JsonProperty("projectList") List<Project> projectList) {
        this.projectManagerPerson = projectManagerPerson;
        this.projectList = projectList;
    }

    public Person getProjectManagerPerson() {
        return projectManagerPerson;
    }

    public void setProjectManagerPerson(Person projectManagerPerson) {
        this.projectManagerPerson = projectManagerPerson;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    @Override
    public String toString() {
        return "ProjectManagerDto{" +
                "projectManagerPerson=" + projectManagerPerson +
                ", projectList=" + projectList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectManagerDto that = (ProjectManagerDto) o;
        return Objects.equals(getProjectManagerPerson(), that.getProjectManagerPerson()) && Objects.equals(getProjectList(), that.getProjectList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectManagerPerson(), getProjectList());
    }
}
