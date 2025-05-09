package org.example.dao;
import org.example.model.Electro;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.util.HibernateUtil;

public class ElectroDao {
    private SessionFactory sessionFactory;

    public ElectroDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void save(Electro electro) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(electro);
        session.getTransaction().commit();
        session.close();
    }

    public Electro getByName(String name) {
        Session session = sessionFactory.openSession();
        Electro electro = session.get(Electro.class, name);
        session.close();
        return electro;
    }
}