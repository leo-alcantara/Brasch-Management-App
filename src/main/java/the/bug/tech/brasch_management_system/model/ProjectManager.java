package the.bug.tech.brasch_management_system.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class ProjectManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectManagerId;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person projectManagerPerson;

    @OneToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "projectManager")
    private List<Project> projectList;

    public ProjectManager() {
    }

    public ProjectManager(Person projectManagerPerson, List<Project> projectList) {
        this.projectManagerPerson = projectManagerPerson;
        this.projectList = projectList;
    }

    public boolean addProject(Project project) {
        return projectList.add(project);
    }

    public boolean removeProject(Project project) {
        return projectList.remove(project);
    }

    public Integer getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(Integer projectManagerId) {
        this.projectManagerId = projectManagerId;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectManager that = (ProjectManager) o;
        return Objects.equals(getProjectManagerPerson(), that.getProjectManagerPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectManagerPerson());
    }

    @Override
    public String toString() {
        return "ProjectManager{" +
                "projectManagerId=" + projectManagerId +
                ", projectManagerPerson=" + projectManagerPerson +
                ", projectList=" + projectList +
                '}';
    }
}
