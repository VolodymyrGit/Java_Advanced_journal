package volm.journal.dao;

import volm.journal.model.Homework;
import volm.journal.model.Lesson;

import java.util.List;

public interface HomeworkDao extends CrudDao<Homework, Long>{

    List<Homework> findByLesson(Lesson lesson);

    List<Homework> findByLessonId(Long id);
}
