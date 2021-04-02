package volm.journal.dao.impl;

import org.hibernate.Session;
import volm.journal.config.HibernateSessionFactory;
import volm.journal.dao.UserDao;
import volm.journal.model.Group;
import volm.journal.model.User;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends CrudDaoImpl<User, Long> implements UserDao {


    public UserDaoImpl() {
        super(User.class);
    }


    @Override
    public List<User> findByGroup(Group group) {

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            List<User> users = session.createQuery("FROM User u WHERE u.group = :group")
                    .setParameter("group", group)
                    .list();

            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    @Override
    public Optional<User> findByEmail(String email) {

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            User user = (User) session.createQuery("FROM User u WHERE u.email = :email")
                    .setParameter("email", email)
                    .getSingleResult();

            return Optional.ofNullable(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
