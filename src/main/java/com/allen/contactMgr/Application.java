package com.allen.contactMgr;

import com.allen.contactMgr.model.Contact;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;


public class Application {
    //Reusable reference to a SessionFactory
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        //Create a StandardServiceRegistry
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new  MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
    public static void main(String[] args) {
        Contact contact = new Contact.ContactBuilder("Tom", "Jones")
                .withEmail("tj@tj.com")
                .withPhone(8882223333L)
                .build();
        //Opens session
        Session session = sessionFactory.openSession();
        //Begins a transaction
        session.beginTransaction();
        //Uses session to save contact
        session.save(contact);
        //Commits the transaction
        session.getTransaction().commit();
        //Closes the session
        session.close();
    }
}
