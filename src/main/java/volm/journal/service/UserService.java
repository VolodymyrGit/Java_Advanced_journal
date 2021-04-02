package volm.journal.service;

import volm.journal.enums.Role;
import volm.journal.model.Group;
import volm.journal.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findByGroup(Group group);

    List<User> findUsersByRole(long group_id, Role role);

    Optional<User> save(User user);

    User findUserByEmail(String email);

    boolean authorized(String email, String password);

    User findUserById(Long id);

    void updateUser(User user);
}
