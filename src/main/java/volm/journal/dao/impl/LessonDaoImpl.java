package volm.journal.dao.impl;

import volm.journal.dao.LessonDao;
import volm.journal.model.Lesson;

import java.sql.SQLException;

public class LessonDaoImpl extends CrudDaoImpl<Lesson, Long> implements LessonDao {

    public LessonDaoImpl() throws SQLException {
        super(Lesson.class);
    }
}
