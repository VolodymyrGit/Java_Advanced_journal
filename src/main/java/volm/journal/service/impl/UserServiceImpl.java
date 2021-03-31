package volm.journal.service.impl;

import volm.journal.dao.UserDao;
import volm.journal.enums.Role;
import volm.journal.model.User;
import volm.journal.service.UserService;
import volm.journal.util.SecurityUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDao();


    @Override
    public List<User> findByGroupId(long group_id) {
        return userDao.findByGroupId(group_id);
    }


    @Override
    public List<User> findUsersByRole(long group_id, Role role) {
        List<User> users = findByGroupId(group_id);
        return users.stream()
                .filter(u -> u.getRole().equals(role))
                .collect(Collectors.toList());
    }


    @Override
    public User save(User user) {
        userDao.save(user);
        return findUserByEmail(user.getEmail());
    }


    @Override
    public User findUserByEmail(String email) {
        return userDao.findByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public boolean authorized(String email, String password) {
        Optional<User> userFromDB = userDao.findByEmail(email);

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
        return userDao.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUserByNewUserAndUserId(user);
    }
}
