package myapp.mapper;

import myapp.dto.CompanyWithEmployee;
import myapp.model.Company;
import myapp.model.User;

import java.util.List;

public class CompanyMapper {

    public static CompanyWithEmployee addEmployee(Company company, List<User> users) {
        return CompanyWithEmployee.builder()
                .id(company.getId())
                .name(company.getName())
                .budget(company.getBudget())
                .employee(users)
                .build();
    }
}
