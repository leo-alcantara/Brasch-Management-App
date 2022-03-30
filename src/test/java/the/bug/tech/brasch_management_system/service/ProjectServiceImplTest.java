package the.bug.tech.brasch_management_system.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import the.bug.tech.brasch_management_system.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProjectServiceImplTest {

    @Autowired
    private CompanyServiceImpl companyService;
    @Autowired
    private ProjectServiceImpl projectService;
    @Autowired
    private ContactPersonServiceImpl contactPersonService;
    @Autowired
    private ProjectManagerServiceImpl projectManagerService;

    private Company arlanda;
    private Company metro;
    private Company globen;
    private Project buildAirport;
    private Project extendLines;
    private Project rebuild;
    private Person personTestOne;
    private Person personTestTwo;
    private Person personTestThree;
    private Person personTestFour;
    private ProjectManager jimmy;
    private ContactPerson denzel;
    private ContactPerson maximus;
    private ContactPerson clint;
    private List<Project> projectListTest;
    private List<Project> projectListTest2;
    private List<Project> projectListTest3;
    private List<Project> projectListTest4;
    private List<ContactPerson> contactListTest1;
    private List<ContactPerson> contactListTest2;
    private List<ContactPerson> contactListTest3;


    @BeforeEach
    void setUp() {
        contactListTest1 = new ArrayList<>();
        contactListTest2 = new ArrayList<>();
        contactListTest3 = new ArrayList<>();
        projectListTest = new ArrayList<>();
        projectListTest2 = new ArrayList<>();
        projectListTest3 = new ArrayList<>();
        projectListTest4 = new ArrayList<>();

        personTestOne = new Person("Denzel Washington", "denzel@denzel.com", "0792654207");
        personTestTwo = new Person("Maximus Decimus Meridius", "maximus@roman.com", "0792574013");
        personTestThree = new Person("Clint Eastwood", "dirt@harry.com", "0799382715");
        personTestFour = new Person("Jimmy Fallon", "jimmy@fallon.com", "0796789542");

        arlanda = new Company("Arlanda", "Airport 10",
                "0800386715", contactListTest1, projectListTest);
        metro = new Company("Metro", "Metro 20",
                "0890765735", contactListTest1, projectListTest2);
        globen = new Company("Globen", "Globe 30",
                "0807854367", contactListTest1, projectListTest3);

        denzel = new ContactPerson(personTestOne, arlanda, projectListTest);
        maximus = new ContactPerson(personTestTwo, metro, projectListTest);
        clint = new ContactPerson(personTestThree, globen, projectListTest);

        contactListTest1.add(denzel);
        contactListTest2.add(maximus);
        contactListTest3.add(clint);

        jimmy = new ProjectManager(personTestFour, projectListTest4);

        buildAirport = new Project("Airport", "Build new airport", "Bromma",
                Project.Status.HAS_NOT_STARTED, LocalDate.parse("2022-12-01"), LocalDate.parse("2026-01-31"),
                arlanda, jimmy, contactListTest1);
        extendLines = new Project("Extension", "Extend metro lines", "Stockholm",
                Project.Status.IN_PROGRESS, LocalDate.parse("2021-06-01"), LocalDate.parse("2024-03-25"),
                metro, jimmy, contactListTest2);
        rebuild = new Project("Rebuild Globen", "Renovate Globens Structure", "Globen",
                Project.Status.COMPLETED, LocalDate.parse("2016-12-01"), LocalDate.parse("2020-01-31"),
                globen, jimmy, contactListTest3);

        //projectListTest.add(buildAirport);
        //projectListTest2.add(extendLines);
        //projectListTest3.add(rebuild);
        projectListTest4.add(buildAirport);
        projectListTest4.add(extendLines);
        projectListTest4.add(rebuild);

        companyService.insertCompany(arlanda);
        companyService.insertCompany(metro);
        companyService.insertCompany(globen);

        projectService.insertProject(buildAirport);
        projectService.insertProject(extendLines);
        projectService.insertProject(rebuild);

        projectManagerService.insertProjectManager(jimmy);

        contactPersonService.insertContactPerson(denzel);
        contactPersonService.insertContactPerson(maximus);
        contactPersonService.insertContactPerson(clint);
    }

    @Test
    void should_get_projects_by_name_return_list() {
        //Arrange
        List<Project> foundProjects;
        //Act
        foundProjects = projectService.getProjectByNameContainsIgnoreCase("Extension");
        //Assert
        assertTrue(foundProjects.contains(extendLines));
        assertEquals("Extension", foundProjects.stream().findFirst().get().getProjectName());
        assertEquals(1, foundProjects.size());
    }

    @Test
    void should_get_projects_by_address_return_list() {
        //Arrange
        List<Project> foundProjects;
        //Act
        foundProjects = projectService.getProjectByAddressContainsIgnoreCase("Bromma");
        //Assert
        assertFalse(foundProjects.isEmpty());
        assertTrue(foundProjects.contains(buildAirport));
        assertEquals("Airport", foundProjects.stream().findFirst().get().getProjectName());
    }

    @Test
    void should_get_projects_by_company_return_list() {
        //Arrange
        List<Project> foundProjects;
        //Act
        foundProjects = projectService.getProjectByCompanyContainsIgnoreCase("Arlanda");
        //Assert
        assertFalse(foundProjects.isEmpty());
        assertTrue(foundProjects.contains(buildAirport));
        assertEquals(1, foundProjects.size());
    }

    @Test
    void should_get_projects_by_project_manager_return_list() {
        //Arrange
        List<Project> foundProjects;
        //Act
        foundProjects = projectService.getProjectByProjectManagerContainsIgnoreCase("Jimmy");
        //Assert
        assertFalse(foundProjects.isEmpty());
        assertTrue(foundProjects.contains(buildAirport));
        assertEquals(3, foundProjects.size());
    }

    @Test
    void should_get_projects_by_contact_person_return_list() {
        //Arrange
        List<Project> foundProjects;
        //Act
        foundProjects = projectService.getProjectByContactPersonContainsIgnoreCase("Maximus");
        //Assert
        assertFalse(foundProjects.isEmpty());
        assertTrue(foundProjects.contains(extendLines));
        assertEquals(1, foundProjects.size());

        System.out.println("PRINT" + foundProjects);
    }
}