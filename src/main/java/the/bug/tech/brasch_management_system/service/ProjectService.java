package the.bug.tech.brasch_management_system.service;

import the.bug.tech.brasch_management_system.model.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjectByNameContainsIgnoreCase(String projectName);

    List<Project> getProjectByAddressContainsIgnoreCase(String projectAddress);

    List<Project> getProjectByCompanyContainsIgnoreCase(String companyName);

    List<Project> getProjectByProjectManagerContainsIgnoreCase(String projectManagerName);

    List<Project> getProjectByContactPersonContainsIgnoreCase(String contactPersonName);
}
