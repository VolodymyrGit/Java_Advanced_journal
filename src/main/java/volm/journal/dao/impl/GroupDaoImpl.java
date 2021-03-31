package volm.journal.dao.impl;

import volm.journal.dao.GroupDao;
import volm.journal.model.Group;

import java.sql.SQLException;

public class GroupDaoImpl extends CrudDaoImpl<Group, Long> implements GroupDao {

    public GroupDaoImpl() throws SQLException {
        super(Group.class);
    }
}
