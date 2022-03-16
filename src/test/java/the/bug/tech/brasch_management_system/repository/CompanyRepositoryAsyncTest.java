package the.bug.tech.brasch_management_system.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import the.bug.tech.brasch_management_system.model.Company;
import the.bug.tech.brasch_management_system.model.ContactPerson;
import the.bug.tech.brasch_management_system.model.Person;
import the.bug.tech.brasch_management_system.model.Project;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestEntityManager
@AutoConfigureTestDatabase
@Transactional
class CompanyRepositoryAsyncTest {

    @Autowired
    private CompanyRepositoryAsync companyRepositoryAsync;
    @Autowired
    private TestEntityManager testEntityManager;

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
        testEntityManager.persist(personTestOne);
        testEntityManager.persist(cPOne);
        testEntityManager.persist(companyTest);
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
    void should_get_all_companies_and_save_as_list() {
        //Arrange
        CompletionStage<U> companyListTest;
        //Act
        companyListTest = companyRepositoryAsync.getAllCompanies();
        //Assert
        assertNotNull(companyListTest);
    }

    @Test
    void deleteCompany() {
    }

    @Test
    void updateCompany() {
    }

    @Test
    void getCompanyByCompanyNameContainsIgnoreCase() {
        CompletionStage<U> companyListTest = null;

        companyListTest = companyRepositoryAsync.getCompanyByCompanyNameContainsIgnoreCase("Test");

        assertNotNull(companyListTest);

    }

    @Test
    void getCompanyByProjectNameContainsIgnoreCase() {
    }

    @Test
    void getCompanyByContactPersonContainsIgnoreCase() {
    }
}