package myapp.client;

import myapp.model.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "COMPANIES-SERVICE")
public interface CompanyClient {

    @GetMapping("/companies/cl/{id}")
    Optional<Company> getJustCompany(@PathVariable Long id);
}
