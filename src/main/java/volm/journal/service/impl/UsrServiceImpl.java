package volm.journal.service.impl;

import volm.journal.dao.UsrDao;
import volm.journal.enums.Role;
import volm.journal.model.Usr;
import volm.journal.service.UsrService;

import java.util.List;
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
}
