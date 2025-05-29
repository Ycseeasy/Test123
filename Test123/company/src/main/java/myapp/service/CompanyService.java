package myapp.service;

import myapp.dto.CompanyWithEmployee;
import myapp.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    CompanyWithEmployee addCompany(Company company);
    CompanyWithEmployee getCompany(Long id);
    List<CompanyWithEmployee> getAll();
    void deleteCompany(Long id);
    CompanyWithEmployee updCompany(Company company);
    Optional<Company> getJustCompany(Long id);
}
