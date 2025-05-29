package myapp.mapper;


import myapp.dto.UserWithComp;
import myapp.model.Company;
import myapp.model.User;

public class UserMapper {

    public static UserWithComp addComp(User user, Company company) {
        return UserWithComp.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .company(company)
                .build();
    }
}
