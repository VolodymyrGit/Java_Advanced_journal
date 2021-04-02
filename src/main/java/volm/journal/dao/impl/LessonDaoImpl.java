package volm.journal.dao.impl;

import org.hibernate.Session;
import volm.journal.config.HibernateSessionFactory;
import volm.journal.dao.LessonDao;
import volm.journal.model.Group;
import volm.journal.model.Lesson;

import java.util.Collections;
import java.util.List;

public class LessonDaoImpl extends CrudDaoImpl<Lesson, Long> implements LessonDao {


    public LessonDaoImpl() {
        super(Lesson.class);
    }


    @Override
    public List<Lesson> findLessonByGroup(Group group) {

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            List<Lesson> lessons = session.createQuery("FROM Lesson l WHERE l.group = :group")
                    .setParameter("group", group)
                    .list();

            return lessons;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
