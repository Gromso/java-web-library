package com.example.Library.hibernate;


import com.example.Library.hibernate.models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;
    private static Session session;

    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Publisher.class);
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Genre.class);
        configuration.addAnnotatedClass(User.class);

        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
                .build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }


    public static Session openSession(){
        if (session != null && session.isOpen()) return session;
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        session = sessionFactory.openSession();
        return session;
    }

    public  static void closeAll(){
        if (session != null && session.isOpen())session.close();
       if (sessionFactory != null) sessionFactory.close();
    }
}
