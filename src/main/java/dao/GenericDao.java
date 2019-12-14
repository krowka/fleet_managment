package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class GenericDao<T> {
    private Class<T> clazz;

    public GenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    private static SessionFactory sessionFactory = null;

    public void save(T t) {
        sessionFactory = getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(t);
        tx.commit();
        session.close();
    }

    public T get(int id) {
        Session session = getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        T t = session.byId(clazz).getReference(id);
        tx.commit();
        session.close();

        return t;
    }


    public boolean remove(int id) {
        Transaction transaction = null;
        boolean result = false;

        try (Session session = getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();

            // Delete a persistent object
            T t = session.get(clazz, id);
            if (t != null) {
                session.delete(t);
                System.out.println("Successfully removed " + clazz.getSimpleName());
                result = true;

            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return result;
    }

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
