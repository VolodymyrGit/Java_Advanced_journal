package volm.journal.dao;

import volm.journal.model.Homework;
import volm.journal.model.Lesson;

import java.util.List;

public interface HomeWorkDao {

    List<Homework> findByLesson(Lesson lesson);
}
