package myapp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import myapp.client.UserClient;
import myapp.dto.CompanyWithEmployee;
import myapp.exception.AlreadyExistsException;
import myapp.exception.CompanyDeleteException;
import myapp.exception.NotFoundException;
import myapp.exception.UpdException;
import myapp.mapper.CompanyMapper;
import myapp.model.Company;
import myapp.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final UserClient userClient;

    @Transactional
    @Override
    public CompanyWithEmployee addCompany(Company company) {
        if (company.getId() != null) {
            if (companyRepository.findById(company.getId()).isPresent()) {
                throw new AlreadyExistsException("Company with ID " + company.getId() + " already exists");
            }
        }
        return CompanyMapper.addEmployee(companyRepository.save(company),List.of());
    }


    @Transactional
    @Override
    public CompanyWithEmployee getCompany(Long id) {
        if (companyRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Company with ID " + id + " was not found");
        }
        return CompanyMapper.addEmployee(companyRepository.findById(id).get(), userClient.getUsersByCompany(id));
    }

    @Transactional
    @Override
    public List<CompanyWithEmployee> getAll() {
        if (!companyRepository.findAll().isEmpty()) {
            return companyRepository.findAll().stream()
                    .map(company -> CompanyMapper.addEmployee(company, userClient.getUsersByCompany(company.getId())))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    @Transactional
    @Override
    public void deleteCompany(Long id) {
        if (companyRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Company with ID " + id + " was not found");
        }
        if (!userClient.getUsersByCompany(id).isEmpty()) {
            throw new CompanyDeleteException("Company with ID " + id + " have employee, remove/replace them");
        }
        companyRepository.deleteById(id);
    }

    @Transactional
    @Override
    public CompanyWithEmployee updCompany(Company company) {
        if (company.getId() == null) {
            throw new UpdException("You cant update company without ID.");
        }
        if (companyRepository.findById(company.getId()).isEmpty()) {
            throw new NotFoundException("Company with ID " + company.getId() + " was not found");
        }

        return CompanyMapper.addEmployee(companyRepository.save(company),
                userClient.getUsersByCompany(company.getId()));
    }

    @Transactional
    @Override
    public Optional<Company> getJustCompany(Long id) {
        return companyRepository.findById(id);
    }
}
