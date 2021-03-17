package volm.journal.dao.impl;

import volm.journal.config.DBConfiguration;
import volm.journal.dao.CrudDao;
import volm.journal.util.SqlQueryUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


public class CrudDaoImpl<T, ID> implements CrudDao<T, ID> {

    private final Connection connection;
    private Class currentClass;

    public CrudDaoImpl(Class currentClass) throws SQLException {
        this.connection = DBConfiguration.getConnection();
        this.currentClass = currentClass;
    }

    @Override
    public void save(T model) {
        String saveQuery = SqlQueryUtil.getSaveQuery(model);

        try(Statement statement = connection.createStatement()) {
            statement.execute(saveQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Optional<T> findById(ID id) {

        return Optional.empty();
    }


    @Override
    public List<T> findAll() {

        return null;
    }

    @Override
    public void delete(T model) {

    }

    @Override
    public T update(T model) {
        return null;
    }


}

