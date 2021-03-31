package volm.journal.dao.impl;

import volm.journal.dao.CrudDao;
import volm.journal.model.HomeWork;

import java.sql.SQLException;


public class HomeWorkDaoImpl extends CrudDaoImpl<HomeWork, Long> implements CrudDao<HomeWork, Long> {

    public HomeWorkDaoImpl() throws SQLException {
        super(HomeWork.class);
    }
}