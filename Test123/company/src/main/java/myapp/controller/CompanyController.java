package myapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myapp.dto.CompanyWithEmployee;
import myapp.model.Company;
import myapp.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/{id}")
    public CompanyWithEmployee getCompany(@PathVariable Long id) {
        log.info("TRY get company with ID:{}", id);
        return companyService.getCompany(id);
    }

    @PostMapping
    public CompanyWithEmployee addCompany(@RequestBody Company company) {
        log.info("TRY add company with body:{}", company);
        return companyService.addCompany(company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        log.info("TRY delete company with ID:{}", id);
        companyService.deleteCompany(id);
    }

    @PatchMapping
    public CompanyWithEmployee updCompany(@RequestBody Company company) {
        log.info("TRY upd company with new body:{}", company);
        return companyService.updCompany(company);
    }

    @GetMapping
    public List<CompanyWithEmployee> getAll() {
        log.info("TRY getAll");
        return companyService.getAll();
    }

    @GetMapping("/cl/{id}")
    public Optional<Company> getJustCompany(@PathVariable Long id) {
        log.info("TRY get company by user with ID:{}", id);
        return companyService.getJustCompany(id);
    }
}
