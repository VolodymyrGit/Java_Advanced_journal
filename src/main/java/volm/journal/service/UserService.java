package volm.journal.service;

import volm.journal.enums.Role;
import volm.journal.model.User;

import java.util.List;

public interface UserService {
    List<User> findByGroupId(long group_id);

    List<User> findUsersByRole(long group_id, Role role);

    User save(User user);

    User findUserByEmail(String email);

    boolean authorized(String email, String password);

    User findUserById(Long id);

    void updateUser(User user);
}
