package myapp.service;

import myapp.client.CompanyClient;
import myapp.dto.UserWithComp;
import myapp.exception.AlreadyExistsException;
import myapp.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import myapp.exception.UpdException;
import myapp.mapper.UserMapper;
import myapp.model.User;
import org.springframework.stereotype.Service;
import myapp.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CompanyClient client;

    @Transactional
    @Override
    public UserWithComp addUser(User user) {
        if (user.getId() != null) {
            if (userRepository.findById(user.getId()).isPresent()) {
                throw new AlreadyExistsException("User with ID " + user.getId() + " already exists");
            }
        }
        if (user.getCompanyId() == null) {
            return UserMapper.addComp(userRepository.save(user), null);
        }
        if (client.getJustCompany(user.getCompanyId()).isEmpty()) {
            throw new NotFoundException("You cant add user with company ID: " + user.getCompanyId());
        }
        return UserMapper.addComp(userRepository.save(user), client.getJustCompany(user.getCompanyId()).get());
    }

    @Transactional
    @Override
    public UserWithComp getUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new NotFoundException("User with ID " + id + " was not found");
        }
        User result = userRepository.findById(id).get();
        if (result.getCompanyId() == null) {
            return UserMapper.addComp(userRepository.save(result), null);
        }
        return UserMapper.addComp(result, client.getJustCompany(result.getCompanyId()).orElse(null));
    }

    @Transactional
    @Override
    public List<UserWithComp> getAll() {
        return userRepository.findAll().stream()
                .map(user -> {
                    if (user.getCompanyId() == null) {
                        return UserMapper.addComp(user, null);
                    }
                    return UserMapper.addComp(user, client.getJustCompany(user.getCompanyId()).orElse(null));
                })
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new NotFoundException("User with ID " + id + " was not found");
        }
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UserWithComp updUser(User user) {
        if (user.getId() == null) {
            throw new UpdException("You cant update user without ID.");
        }
        if (user.getCompanyId() == null) {
            return UserMapper.addComp(userRepository.save(user), null);
        }
        if (client.getJustCompany(user.getCompanyId()).isEmpty()) {
            throw new NotFoundException("You cant upd user with company ID: " + user.getCompanyId());
        }
        if (userRepository.findById(user.getId()).isEmpty()) {
            throw new NotFoundException("User with ID " + user.getId() + " was not found");
        }
        return UserMapper.addComp(userRepository.save(user),
                client.getJustCompany(user.getCompanyId()).orElse(null));
    }

    @Transactional
    @Override
    public List<User> getUsersByCompanyId(Long id) {
        return userRepository.getUsersByCompanyId(id);
    }
}
