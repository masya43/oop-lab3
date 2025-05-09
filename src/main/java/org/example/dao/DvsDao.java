package org.example.dao;
import org.example.model.Dvs;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.util.HibernateUtil;

public class DvsDao {
    private SessionFactory sessionFactory;

    public DvsDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void save(Dvs dvs) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(dvs);
        session.getTransaction().commit();
        session.close();
    }

    public Dvs getByName(String name) {
        Session session = sessionFactory.openSession();
        Dvs dvs = session.get(Dvs.class, name);
        session.close();
        return dvs;
    }
}