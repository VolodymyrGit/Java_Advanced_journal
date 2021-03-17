package volm.journal.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    void save(T model);

    void delete(T model);

    T update(T model);
}
