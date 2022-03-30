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
class ContactPersonServiceImplTest {

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

        jimmy = new ProjectManager(personTestFour, projectListTest4);

        arlanda = new Company("Arlanda", "Airport 10",
                "0800386715", contactListTest1, projectListTest);
        globen = new Company("Globen", "Globe 30",
                "0807854367", contactListTest2, projectListTest3);
        metro = new Company("Metro", "Metro 20",
                "0890765735", contactListTest3, projectListTest2);


        denzel = new ContactPerson(personTestOne, arlanda, projectListTest);
        maximus = new ContactPerson(personTestTwo, metro, projectListTest2);
        clint = new ContactPerson(personTestThree, globen, projectListTest3);

        //contactListTest1.add(denzel);
        //contactListTest2.add(maximus);
        //contactListTest3.add(clint);

        buildAirport = new Project("Airport", "Build new airport", "Bromma",
                Project.Status.HAS_NOT_STARTED, LocalDate.parse("2022-12-01"), LocalDate.parse("2026-01-31"),
                arlanda, jimmy, contactListTest1);
        extendLines = new Project("Extension", "Extend metro lines", "Stockholm",
                Project.Status.IN_PROGRESS, LocalDate.parse("2021-06-01"), LocalDate.parse("2024-03-25"),
                metro, jimmy, contactListTest2);
        rebuild = new Project("Rebuild Globen", "Renovate Globens Structure", "Globen",
                Project.Status.COMPLETED, LocalDate.parse("2016-12-01"), LocalDate.parse("2020-01-31"),
                globen, jimmy, contactListTest3);

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
    void should_get_contact_person_by_name__return_list() {
        //Arrange
        List<ContactPerson> foundPeople;
        //Act
        foundPeople = contactPersonService.getContactPersonByNameContainsIgnoreCase("denzel");
        //Assert
        assertFalse(foundPeople.isEmpty());
        assertTrue(foundPeople.contains(denzel));
        assertEquals(1, foundPeople.size());
    }

    @Test
    void should_get_contact_person_by_company_return_list() {
        //Arrange
        List<ContactPerson> foundPeople;
        //Act
        foundPeople = contactPersonService.getContactPersonByCompanyContainsIgnoreCase("Globen");
        //Assert
        assertFalse(foundPeople.isEmpty());
        assertTrue(foundPeople.contains(clint));
        assertEquals(1, foundPeople.size());

        System.out.println(foundPeople);
    }

    @Test
    void should_get_contact_person_by_project_return_list() {
        //Arrange
        List<ContactPerson> foundPeople;
        //Act
        foundPeople = contactPersonService.getContactPersonByProjectContainsIgnoreCase("Airport");
        //Assert
        assertFalse(foundPeople.isEmpty());
        assertTrue(foundPeople.contains(denzel));
        assertEquals(1, foundPeople.size());
    }
}