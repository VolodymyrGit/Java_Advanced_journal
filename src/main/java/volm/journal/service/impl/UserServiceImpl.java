package volm.journal.service.impl;

import volm.journal.dao.impl.UserDaoImpl;
import volm.journal.enums.Role;
import volm.journal.model.Group;
import volm.journal.model.User;
import volm.journal.service.UserService;
import volm.journal.util.SecurityUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDaoImpl = new UserDaoImpl();


    @Override
    public List<User> findByGroup(Group group) {
        return userDaoImpl.findByGroup(group);
    }


    @Override
    public List<User> findUsersByRole(long group_id, Role role) {
        List<User> users = findByGroupId(group_id);
        return users.stream()
                .filter(u -> u.getRole().equals(role))
                .collect(Collectors.toList());
    }


    @Override
    public Optional<User> save(User user) {
        userDaoImpl.save(user);
        return findUserByEmail(user.getEmail());
    }


    @Override
    public User findUserByEmail(String email) {
        return userDaoImpl.findByEmail(email)
                .orElseThrow(NoSuchElementException::new);
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

    @Override
    public User findUserById(Long id) {
        return userDaoImpl.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void updateUser(User user) {
        userDaoImpl.updateUserByNewUserAndUserId(user);
    }
}
