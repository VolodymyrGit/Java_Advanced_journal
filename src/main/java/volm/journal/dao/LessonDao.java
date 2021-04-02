package volm.journal.dao;

import volm.journal.model.Group;
import volm.journal.model.Lesson;

import java.util.List;

public interface LessonDao {

    List<Lesson> findLessonByGroup(Group group);
}
