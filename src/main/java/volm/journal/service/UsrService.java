package volm.journal.service;

import volm.journal.enums.Role;
import volm.journal.model.Usr;

import java.util.List;

public interface UsrService {
    List<Usr> findByGroupId(long group_id);

    List<Usr> findUsersByRole(long group_id, Role role);

    Usr save(Usr user);

    Usr findUsrByEmail(String email);

    boolean authorized(String email, String password);
}
