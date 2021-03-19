package volm.journal.dao.impl;

import volm.journal.dao.GroupDao;
import volm.journal.model.Sgroup;

import java.sql.SQLException;

public class GroupDaoImpl extends CrudDaoImpl<Sgroup, Long> implements GroupDao {

    public GroupDaoImpl() throws SQLException {
        super(Sgroup.class);
    }
}
