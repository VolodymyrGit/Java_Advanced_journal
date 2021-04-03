package volm.journal.service.impl;

import volm.journal.dao.impl.UserDaoImpl;
import volm.journal.enums.Role;
import volm.journal.model.Group;
import volm.journal.model.User;
import volm.journal.service.UserService;
import volm.journal.util.SecurityUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDaoImpl = new UserDaoImpl();


    @Override
    public List<User> findUsersByRole(Group group, Role role) {
        List<User> users = userDaoImpl.findByGroup(group);
        return users.stream()
                .filter(u -> u.getRole().equals(role))
                .collect(Collectors.toList());
    }



    @Override
    public boolean authorized(String email, String password) {
        Optional<User> userFromDB = userDaoImpl.findByEmail(email);

        if(userFromDB.isPresent()) {

            User user = userFromDB.get();
            String securePassword = SecurityUtil.getSecurePassword(password, user.getSalt());

            if(user.getPassword().equals(securePassword)) {
                return true;
            }
        }
        return false;
    }
}
