package the.bug.tech.brasch_management_system.service;

import org.springframework.stereotype.Component;
import the.bug.tech.brasch_management_system.model.*;
import the.bug.tech.brasch_management_system.model.dto.*;

@Component
public class EntityDtoMapper {

    public Company toCompany(CompanyDto companyDto) {
        return new Company(companyDto.getCompanyName(),
                companyDto.getCompanyAddress(),
                companyDto.getCompanyPhoneNumber(),
                companyDto.getContactPersonList(),
                companyDto.getProjectsList());
    }

    public CompanyDto toCompanyDto(Company company) {
        return new CompanyDto(company.getCompanyName(),
                company.getCompanyAddress(),
                company.getCompanyPhoneNumber(),
                company.getContactPersonList(),
                company.getProjectsList());
    }

    public ContactPerson toContactPerson(ContactPersonDto contactPersonDto){
        return new ContactPerson(contactPersonDto.getContactPersonPerson(),
                contactPersonDto.getCompany(),
                contactPersonDto.getProjectList());
    }

    public ContactPersonDto toContactPersonDto(ContactPerson contactPerson) {
        return new ContactPersonDto(contactPerson.getContactPersonPerson(),
                contactPerson.getCompany(),
                contactPerson.getProjectList());
    }

    public Person toPerson(PersonDto personDto) {
        return new Person(personDto.getFirstName(),
                personDto.getLastName(),
                personDto.getEmail(),
                personDto.getPhoneNumber());
    }

    public PersonDto toPersonDto(Person person) {
        return new PersonDto(person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getPhoneNumber());
    }

    public Project toProject(ProjectDto projectDto) {
        return new Project(projectDto.getProjectName(),
                projectDto.getProjectDescription(),
                projectDto.getProjectLocal(),
                projectDto.getStatus(),
                projectDto.getProjectedStartDate(),
                projectDto.getProjectedConclusionDate(),
                projectDto.getCompany(),
                projectDto.getProjectManager(),
                projectDto.getContactPerson());
    }

    public ProjectDto toProjectDto(Project project) {
        return new ProjectDto(project.getProjectName(),
                project.getProjectDescription(),
                project.getProjectLocal(),
                project.getProjectStatus(),
                project.getProjectedStartDate(),
                project.getProjectedConclusionDate(),
                project.getCompany(),
                project.getProjectManager(),
                project.getContactPerson());
    }

    public ProjectManager toProjectManager(ProjectManagerDto projectManagerDto) {
        return new ProjectManager(projectManagerDto.getProjectManagerPerson(),
                projectManagerDto.getProjectList());
    }

    public ProjectManagerDto toProjectManagerDto(ProjectManager projectManager) {
        return new ProjectManagerDto(projectManager.getProjectManagerPerson(),
                projectManager.getProjectList());
    }


}
