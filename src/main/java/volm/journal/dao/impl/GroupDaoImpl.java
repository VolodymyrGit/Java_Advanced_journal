package volm.journal.dao.impl;

import volm.journal.dao.GroupDao;
import volm.journal.model.Group;


public class GroupDaoImpl extends CrudDaoImpl<Group, Long> implements GroupDao {

    public GroupDaoImpl() {
        super(Group.class);
    }
}
