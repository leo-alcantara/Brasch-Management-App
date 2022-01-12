package the.bug.tech.brasch_management_system.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import the.bug.tech.brasch_management_system.model.ContactPerson;
import the.bug.tech.brasch_management_system.model.Project;

import java.util.List;
import java.util.Objects;

public class CompanyDto {

    private String companyName;
    private String companyAddress;
    private String companyPhoneNumber;
    private List<ContactPerson> contactPersonList;
    private List<Project> projectsList;

    @JsonCreator
    public CompanyDto(@JsonProperty("companyName") String companyName,
                      @JsonProperty("companyAddress") String companyAddress,
                      @JsonProperty("companyPhoneNumber") String companyPhoneNumber,
                      @JsonProperty("contactPersonList") List<ContactPerson> contactPersonList,
                      @JsonProperty("projectList") List<Project> projectsList) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyPhoneNumber = companyPhoneNumber;
        this.contactPersonList = contactPersonList;
        this.projectsList = projectsList;
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
    public String toString() {
        return "CompanyDto{" +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyPhoneNumber='" + companyPhoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDto that = (CompanyDto) o;
        return Objects.equals(getCompanyName(), that.getCompanyName()) && Objects.equals(getCompanyAddress(), that.getCompanyAddress()) && Objects.equals(getCompanyPhoneNumber(), that.getCompanyPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCompanyName(), getCompanyAddress(), getCompanyPhoneNumber());
    }
}
