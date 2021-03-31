package volm.journal.service.impl;

import volm.journal.dao.UsrDao;
import volm.journal.enums.Role;
import volm.journal.model.Usr;
import volm.journal.service.UsrService;
import volm.journal.util.SecurityUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


public class UsrServiceImpl implements UsrService {

    private final UsrDao usrDao = new UsrDao();


    @Override
    public List<Usr> findByGroupId(long group_id) {
        return usrDao.findByGroupId(group_id);
    }


    @Override
    public List<Usr> findUsersByRole(long group_id, Role role) {
        List<Usr> users = findByGroupId(group_id);
        return users.stream()
                .filter(u -> u.getRole().equals(role))
                .collect(Collectors.toList());
    }


    @Override
    public Usr save(Usr user) {
        usrDao.save(user);
        return findUsrByEmail(user.getEmail());
    }


    @Override
    public Usr findUsrByEmail(String email) {
        return usrDao.findByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public boolean authorized(String email, String password) {
        Optional<Usr> userFromDB = usrDao.findByEmail(email);

        if(userFromDB.isPresent()) {
            Usr user = userFromDB.get();
            String securePassword = SecurityUtil.getSecurePassword(password, user.getSalt());
            if(user.getPassword().equals(securePassword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Usr findUserById(Long id) {
        return usrDao.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void updateUser(Usr user) {
        usrDao.updateUserByNewUserAndUserId(user);
    }
}
