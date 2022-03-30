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
class CompanyServiceImplTest {

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
    private List<ContactPerson> contactListTest;

    @BeforeEach
    void setUp() {
        contactListTest = new ArrayList<>();
        projectListTest = new ArrayList<>();
        projectListTest2 = new ArrayList<>();
        projectListTest3 = new ArrayList<>();
        projectListTest4 = new ArrayList<>();


        personTestOne = new Person("Denzel Washington", "denzel@denzel.com", "0792654207");
        personTestTwo = new Person("Maximus Decimus Meridius", "maximus@roman.com", "0792574013");
        personTestThree = new Person("Clint Eastwood", "dirt@harry.com", "0799382715");
        personTestFour = new Person("Jimmy Fallon", "jimmy@fallon.com", "0796789542");

        jimmy = new ProjectManager(personTestFour, projectListTest4);

        arlanda = new Company("Arlanda", "Airport 10",
                "0800386715", contactListTest, projectListTest);
        metro = new Company("Metro", "Metro 20",
                "0890765735", contactListTest, projectListTest2);
        globen = new Company("Globen", "Globe 30",
                "0807854367", contactListTest, projectListTest3);

        denzel = new ContactPerson(personTestOne, arlanda, projectListTest);
        maximus = new ContactPerson(personTestTwo, metro, projectListTest);
        clint = new ContactPerson(personTestThree, globen, projectListTest);


        buildAirport = new Project("Airport", "Build new airport", "Bromma",
                Project.Status.HAS_NOT_STARTED, LocalDate.parse("2022-12-01"), LocalDate.parse("2026-01-31"),
                arlanda, jimmy, contactListTest);
        extendLines = new Project("Extension", "Extend metro lines", "Stockholm",
                Project.Status.IN_PROGRESS, LocalDate.parse("2021-06-01"), LocalDate.parse("2024-03-25"),
                metro, jimmy, contactListTest);
        rebuild = new Project("Rebuild Globen", "Renovate Globens Structure", "Globen",
                Project.Status.COMPLETED, LocalDate.parse("2016-12-01"), LocalDate.parse("2020-01-31"),
                globen, jimmy, contactListTest);

        contactListTest.add(denzel);
        contactListTest.add(maximus);
        contactListTest.add(clint);

        projectListTest.add(buildAirport);
        projectListTest2.add(extendLines);
        projectListTest3.add(rebuild);
        projectListTest4.add(buildAirport);
        projectListTest4.add(extendLines);
        projectListTest4.add(rebuild);

        projectManagerService.insertProjectManager(jimmy);

        projectService.insertProject(buildAirport);
        projectService.insertProject(extendLines);
        projectService.insertProject(rebuild);

        contactPersonService.insertContactPerson(denzel);
        contactPersonService.insertContactPerson(maximus);
        contactPersonService.insertContactPerson(clint);

        companyService.insertCompany(arlanda);
        companyService.insertCompany(metro);
        companyService.insertCompany(globen);
    }

    @Test
    void should_get_company_by_company_name_return_list() {
        //Arrange
        List<Company> foundCompany;
        //Act
        foundCompany = companyService.getCompanyByCompanyNameContainsIgnoreCase("Arlanda");
        //Assert
        assertEquals(1, foundCompany.size());
        assertTrue(foundCompany.contains(arlanda));
        assertEquals(arlanda, foundCompany.get(0));
    }

    @Test
    void should_get_company_by_project_name_return_list() {
        //Arrange
        List<Company> foundCompany;
        //Act
        foundCompany = companyService.getCompanyByProjectNameContainsIgnoreCase("Extension");
        //Assert
        assertFalse(foundCompany.isEmpty());
        assertEquals(metro, foundCompany.get(0));
        assertEquals(1, foundCompany.size());
        assertTrue(foundCompany.contains(metro));
    }

    @Test
    void should_get_company_by_contact_person_name_return_list() {
        //Arrange
        List<Company> foundCompany;
        //Act
        foundCompany = companyService.getCompanyByContactPersonContainsIgnoreCase("zel");
        //Assert
        assertFalse(foundCompany.isEmpty());
        assertEquals(1, foundCompany.size());
        assertTrue(foundCompany.contains(arlanda));
    }
}