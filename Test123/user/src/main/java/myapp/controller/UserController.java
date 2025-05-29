package myapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myapp.dto.UserWithComp;
import myapp.model.User;
import org.springframework.web.bind.annotation.*;
import myapp.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserWithComp getUser(@PathVariable Long id) {
        log.info("TRY getUser with ID:{}", id);
        return userService.getUser(id);
    }

    @PostMapping
    public UserWithComp addUser(@RequestBody User user) {
        log.info("TRY addUser with body:{}", user);
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        log.info("TRY deleteUser with ID:{}", id);
        userService.deleteUser(id);
    }

    @PatchMapping
    public UserWithComp updUser(@RequestBody User user) {
        log.info("TRY updUser with body:{}", user);
        return userService.updUser(user);
    }

    @GetMapping
    public List<UserWithComp> getAll() {
        log.info("TRY getAll");
        return userService.getAll();
    }

    @GetMapping("/cl/{id}")
    public List<User> getUsersByCompany(@PathVariable Long id) {
        log.info("TRY getUsersByCompany with ID:{}", id);
        return userService.getUsersByCompanyId(id);
    }
}
