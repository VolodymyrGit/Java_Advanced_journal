package volm.journal.dao.impl;

import volm.journal.dao.CrudDao;
import volm.journal.model.Lesson;

import java.sql.SQLException;

public class LessonDaoImpl extends CrudDaoImpl<Lesson, Long> implements CrudDao<Lesson, Long> {

    public LessonDaoImpl() throws SQLException {
        super(Lesson.class);
    }
}
