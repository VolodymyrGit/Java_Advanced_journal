package volm.journal.dao;

import volm.journal.model.Group;
import volm.journal.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudDao<User, Long>{

    List<User> findByGroup(Group group);

    List<User> FindByGroupId(Long groupId);

    Optional<User> findByEmail(String email);
}
