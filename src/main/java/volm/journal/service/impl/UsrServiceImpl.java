package volm.journal.service.impl;

import volm.journal.dao.UsrDao;
import volm.journal.model.Usr;
import volm.journal.service.UsrService;

import java.util.List;

public class UsrServiceImpl implements UsrService {

    private final UsrDao usrDao = new UsrDao();

    @Override
    public List<Usr> findByGroupId(long group_id) {
        return usrDao.findByGroupId(group_id);
    }
}
