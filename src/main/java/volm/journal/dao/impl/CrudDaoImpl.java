package volm.journal.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import volm.journal.config.HibernateSessionFactory;
import volm.journal.dao.CrudDao;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class CrudDaoImpl<T, ID extends Serializable> implements CrudDao<T, ID> {

    private Class currentClass;

    public CrudDaoImpl(Class currentClass) {
        this.currentClass = currentClass;
    }


    @Override
    public List<T> findAll() {

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            List<T> models = session.createQuery("FROM " + currentClass.getClass().getCanonicalName())
                    .list();

            return models;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }


    @Override
    public Optional<T> findById(ID id) {

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            T model = (T) session.get(currentClass, id);

            return Optional.ofNullable(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public Optional<T> save(T model) {

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {

            Transaction transaction = session.beginTransaction();
            ID id = (ID) session.save(model);
            transaction.commit();

            return Optional.ofNullable((T) session.get(model.getClass(), id));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }



    @Override
    public void delete(T model) {

        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.delete(model);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(T model) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.update(model);

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

