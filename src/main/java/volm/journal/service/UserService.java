package volm.journal.service;

import volm.journal.enums.Role;
import volm.journal.model.Group;
import volm.journal.model.User;

import java.util.List;

public interface UserService {

    List<User> findUsersByRole(Group group, Role role);

    boolean authorized(String email, String password);
}
