package myapp.dto;

import lombok.Builder;
import lombok.Data;
import myapp.model.Company;

@Data
@Builder
public class UserWithComp {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private Company company;
}
