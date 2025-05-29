package myapp.service;

import myapp.dto.UserWithComp;
import myapp.model.User;

import java.util.List;

public interface UserService {
    UserWithComp addUser(User user);
    UserWithComp getUser(Long id);
    List<UserWithComp> getAll();
    void deleteUser(Long id);
    UserWithComp updUser(User user);
    List<User> getUsersByCompanyId(Long id);
}
