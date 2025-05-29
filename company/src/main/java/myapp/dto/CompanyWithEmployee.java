package myapp.dto;

import lombok.Builder;
import lombok.Data;
import myapp.model.User;

import java.util.List;

@Data
@Builder
public class CompanyWithEmployee {

    private Long id;
    private String name;
    private Long budget;
    private List<User> employee;
}
