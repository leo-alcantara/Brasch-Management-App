package the.bug.tech.brasch_management_system.resource;

import io.vavr.control.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.model.Company;
import the.bug.tech.brasch_management_system.service.CompanyService;

import java.util.List;
import java.util.concurrent.CompletionStage;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/api/company")
    public ResponseEntity<Company> create(@RequestBody Company company) {
        Company saved = companyService.createCompany(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/api/company/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") String companyId) {
        Company companyFoundById = companyService.findById(companyId);
        return ResponseEntity.ok(companyFoundById);
    }

    @GetMapping("/api/company")
    public ResponseEntity<List<Company>> findAll() {
        List<Company> allFound = companyService.findAll();
        return ResponseEntity.ok(allFound);
    }

    @PutMapping("/api/company/{id}")
    public ResponseEntity<Company> update(@PathVariable("id") String companyId,
                                          @RequestBody Company company) {
        if (companyId.equals(company.getCompanyId())) {
            Company updatedCompany = companyService.update(company);
            return ResponseEntity.ok().body(updatedCompany);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/api/company/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String companyId) {
        companyService.delete(companyId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/api/company/{companyName}")
    public ResponseEntity<CompletionStage<Option<Company>>> findCompanyByCompanyNameContainsIgnoreCase(@PathVariable("companyName") String companyName) {
        CompletionStage<Option<Company>> foundCompany = companyService.findCompanyByCompanyNameContainsIgnoreCase(companyName);
        return ResponseEntity.ok(foundCompany);
    }

    @GetMapping("/api/company/{projectName}")
    public ResponseEntity<CompletionStage<Option<Company>>> findCompanyByProjectNameContainsIgnoreCase(@PathVariable("projectName") String projectName) {
        CompletionStage<Option<Company>> foundCompanies = companyService.findCompanyByProjectNameContainsIgnoreCase(projectName);
        return ResponseEntity.ok(foundCompanies);
    }

    @GetMapping("/api/company/{name}")
    public ResponseEntity<CompletionStage<List<Company>>> findCompanyByContactPersonContainsIgnoreCase(@PathVariable("name") String name) {
        CompletionStage<List<Company>> foundCompanies = companyService.findCompanyByContactPersonContainsIgnoreCase(name);
        return ResponseEntity.ok(foundCompanies);
    }
}
