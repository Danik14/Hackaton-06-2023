package daniyar.hackaton.service;

import java.util.List;
import java.util.UUID;

import daniyar.hackaton.data.User;

public interface UserService {
    // User createUser(User user);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    User getUserById(UUID id);

    void deleteUser(UUID id);
}
