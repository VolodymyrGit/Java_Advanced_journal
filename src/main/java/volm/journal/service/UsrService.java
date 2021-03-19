package volm.journal.service;

import volm.journal.model.Usr;

import java.util.List;

public interface UsrService {
    List<Usr> findByGroupId(long group_id);
}
