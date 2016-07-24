package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.data.ContactData;
import ru.stqa.pft.addressbook.data.GroupData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();

        sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<GroupData> resultGroup = session.createQuery("from GroupData").list();
        for ( GroupData group : resultGroup ) { System.out.println(group); }

        session.getTransaction().commit();
        session.close();

        return new Groups(resultGroup);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<ContactData> resultContact = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        for ( ContactData contact : resultContact ) { System.out.println(contact); }

        session.getTransaction().commit();
        session.close();

        return new Contacts(resultContact);
    }
}
