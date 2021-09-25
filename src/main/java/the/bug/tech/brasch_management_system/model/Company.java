package the.bug.tech.brasch_management_system.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;
    private String companyName;
    private String companyAddress;
    private String companyPhoneNumber;

    @OneToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY,
    mappedBy = "company")
    private List<ContactPerson> contactPersonList;

    @OneToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            mappedBy = "company")
    private List<Project> projectsList;

    public Company() {
    }

    public Company(String companyName, String companyAddress, String companyPhoneNumber,
                   List<ContactPerson> contactPersonList, List<Project> projectsList) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyPhoneNumber = companyPhoneNumber;
        this.contactPersonList = contactPersonList;
        this.projectsList = projectsList;
    }

    //Convenience Methods(Project, ContactPerson)

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public List<ContactPerson> getContactPersonList() {
        return contactPersonList;
    }

    public void setContactPersonList(List<ContactPerson> contactPersonList) {
        this.contactPersonList = contactPersonList;
    }

    public List<Project> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Project> projectsList) {
        this.projectsList = projectsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(getCompanyName(), company.getCompanyName()) && Objects.equals(getCompanyAddress(), company.getCompanyAddress()) && Objects.equals(getCompanyPhoneNumber(), company.getCompanyPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompanyName(), getCompanyAddress(), getCompanyPhoneNumber());
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyPhoneNumber='" + companyPhoneNumber + '\'' +
                ", contactPersonList=" + contactPersonList +
                ", projectsList=" + projectsList +
                '}';
    }
}
