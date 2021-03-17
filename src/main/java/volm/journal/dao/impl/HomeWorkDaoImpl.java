package volm.journal.dao.impl;

import volm.journal.dao.HomeWorkDao;
import volm.journal.model.HomeWork;

import java.sql.SQLException;


public class HomeWorkDaoImpl extends CrudDaoImpl<HomeWork, Long> implements HomeWorkDao {

    public HomeWorkDaoImpl() throws SQLException {
        super(HomeWork.class);
    }
}