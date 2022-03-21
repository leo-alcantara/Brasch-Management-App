package the.bug.tech.brasch_management_system.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import the.bug.tech.brasch_management_system.model.Company;
import the.bug.tech.brasch_management_system.model.dto.CompanyDto;
import the.bug.tech.brasch_management_system.service.CompanyServiceImpl;
import the.bug.tech.brasch_management_system.service.EntityDtoMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/company")
public class CompanyResource {

    private final CompanyServiceImpl companyServiceImpl;
    private final EntityDtoMapper mapper;

    @Autowired
    public CompanyResource(CompanyServiceImpl companyServiceImpl, EntityDtoMapper mapper) {
        this.companyServiceImpl = companyServiceImpl;
        this.mapper = mapper;
    }

    @RequestMapping(produces = "application/json", value = "/create", method = RequestMethod.POST)
    public ResponseEntity<CompanyDto> insertCompany(@RequestBody CompanyDto companyDto) {
        Company insertedCompany = companyServiceImpl.insertCompany(mapper.toCompany(companyDto));
        CompanyDto insertedCompanyDto = mapper.toCompanyDto(insertedCompany);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedCompanyDto);
    }

    @RequestMapping(produces = "application/json", value = "/getById", method = RequestMethod.GET)
    public ResponseEntity<CompanyDto> getCompanyById(@RequestParam Integer companyId) {
        Company companyFoundById = companyServiceImpl.getCompanyById(companyId);
        CompanyDto companyFoundByIdDto = mapper.toCompanyDto(companyFoundById);
        return ResponseEntity.ok(companyFoundByIdDto);
    }

    @RequestMapping(produces = "application/json", value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<Company> companies = companyServiceImpl.getAllCompanies();
        List<CompanyDto> companiesDto = companies
                .stream()
                .map(mapper::toCompanyDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(companiesDto);
    }

    @RequestMapping(produces = "application/json", value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<CompanyDto> updateCompany(Integer companyToUpdateId,
                                                    @RequestBody CompanyDto updateCompanyDto) {
        Company convertedCompany = companyServiceImpl.updateCompany(companyToUpdateId, mapper.toCompany(updateCompanyDto));
        CompanyDto convertedCompanyDto = mapper.toCompanyDto(convertedCompany);
        return ResponseEntity.ok().body(convertedCompanyDto);
    }

    @RequestMapping(produces = "application/json", value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCompany(@RequestBody Integer companyId) {
        companyServiceImpl.deleteCompanyById(companyId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(produces = "application/json", value = "/getByName", method = RequestMethod.GET)
    public ResponseEntity<List<CompanyDto>> getCompanyByCompanyNameContainsIgnoreCase(@RequestParam String companyName) {
        List<Company> companiesFoundByName = companyServiceImpl.getCompanyByCompanyNameContainsIgnoreCase(companyName);
        List<CompanyDto> companiesFoundByNameDto = companiesFoundByName
                .stream()
                .map(mapper::toCompanyDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(companiesFoundByNameDto);
    }

    @RequestMapping(produces = "application/json", value = "/getByProject", method = RequestMethod.GET)
    public ResponseEntity<List<CompanyDto>> getCompanyByProjectNameContainsIgnoreCase(@RequestParam String projectName) {
        List<Company> companiesFoundByProject = companyServiceImpl.getCompanyByProjectNameContainsIgnoreCase(projectName);
        List<CompanyDto> companiesFoundByProjectDto = companiesFoundByProject
                .stream()
                .map(mapper::toCompanyDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(companiesFoundByProjectDto);
    }

    @RequestMapping(produces = "application/json", value = "/getByContactPerson", method = RequestMethod.GET)
    public ResponseEntity<List<CompanyDto>> getCompanyByContactPersonContainsIgnoreCase(@RequestParam String contactPersonName) {
        List<Company> companiesFoundByContact = companyServiceImpl.getCompanyByContactPersonContainsIgnoreCase(contactPersonName);
        List<CompanyDto> companiesFoundByContactDto = companiesFoundByContact
                .stream()
                .map(mapper::toCompanyDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(companiesFoundByContactDto);
    }
}
