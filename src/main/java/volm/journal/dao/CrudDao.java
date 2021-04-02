package volm.journal.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudDao<T, ID extends Serializable> {

    List<T> findAll();

    Optional<T> findById(ID id);

    Optional<T> save(T model);

    void delete(T model);

    void update(T model);
}
