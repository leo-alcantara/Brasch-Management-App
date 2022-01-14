package the.bug.tech.brasch_management_system.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import the.bug.tech.brasch_management_system.config.YMLConfiguration;
import the.bug.tech.brasch_management_system.model.Company;
import the.bug.tech.brasch_management_system.model.ContactPerson;
import the.bug.tech.brasch_management_system.model.Person;
import the.bug.tech.brasch_management_system.model.Project;
import the.bug.tech.brasch_management_system.model.dto.CompanyDto;

import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = YMLConfiguration.class)
@ActiveProfiles("test")
class CompanyRepositoryAsyncTest {

    @Mock
    private CompanyRepositoryAsync companyRepositoryAsync;

    @BeforeEach
    void setUp() {
        List<ContactPerson> contactListTest = new ArrayList<>();
        List<Project> projectListTest = new ArrayList<>();
        Project projectTest = new Project();
        Person personTestOne = new Person("name test one", "surname test one", "email test one", "phone test one");
        Person personTestTwo = new Person("name test two", "surname test two", "email test two", "phone test two");
        Company companyTest = new Company("Test Company", "Test Street", "000000000", contactListTest, projectListTest);
        ContactPerson cPOne = new ContactPerson(personTestOne, companyTest, projectListTest);
        contactListTest.add(cPOne);
        projectListTest.add(projectTest);
        companyRepositoryAsync.insertCompany(companyTest);


    }

    @Test
    void insertCompany() {
        //Arrange

        //Act


        //Assert
    }

    @Test
    void getCompanyById() {
        //Arrange

        //Act


        //Assert
    }

    @Test
    void getAllCompanies() {
        //Arrange
        List<Company> companyListTest;
        //Act


        //Assert

    }

    @Test
    void deleteCompany() {
    }

    @Test
    void updateCompany() {
    }

    @Test
    void getCompanyByCompanyNameContainsIgnoreCase() {
    }

    @Test
    void getCompanyByProjectNameContainsIgnoreCase() {
    }

    @Test
    void getCompanyByContactPersonContainsIgnoreCase() {
    }
}