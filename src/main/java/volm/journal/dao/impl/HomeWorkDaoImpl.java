package volm.journal.dao.impl;

import org.hibernate.Session;
import volm.journal.config.HibernateSessionFactory;
import volm.journal.dao.HomeWorkDao;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;


import java.util.Collections;
import java.util.List;


public class HomeWorkDaoImpl extends CrudDaoImpl<Homework, Long> implements HomeWorkDao {


    public HomeWorkDaoImpl() {
        super(Homework.class);
    }


    @Override
    public List<Homework> findByLesson(Lesson lesson) {

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            List<Homework> homeworks = session.createQuery("FROM Homework h WHERE h.lesson = :lesson")
                    .setParameter("lesson", lesson)
                    .list();

            return homeworks;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
