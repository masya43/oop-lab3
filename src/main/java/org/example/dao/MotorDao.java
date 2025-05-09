package org.example.dao;
import org.example.model.Motor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.util.HibernateUtil;
import java.util.List;
import org.hibernate.Transaction;

public class MotorDao {
    private SessionFactory sessionFactory;

    public MotorDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void save(Motor motor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(motor);
        session.getTransaction().commit();
        session.close();
    }

    public Motor getByName(String name) {
        Session session = sessionFactory.openSession();
        Motor motor = session.get(Motor.class, name);
        session.close();
        return motor;
    }
    public List<Motor> getAll() {
        Session session = sessionFactory.openSession();
        List<Motor> list = session.createQuery("from Motor", Motor.class).list();
        session.close();
        return list;
    }

    public Motor getById(int id) {
        Session session = sessionFactory.openSession();
        Motor motor = session.get(Motor.class, id);
        session.close();
        return motor;
    }

    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Motor motor = session.get(Motor.class, id);
        if (motor != null) {
            session.delete(motor);
        }

        tx.commit();
        session.close();
    }
}