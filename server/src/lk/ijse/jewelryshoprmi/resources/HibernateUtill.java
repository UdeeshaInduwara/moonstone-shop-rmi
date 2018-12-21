package lk.ijse.jewelryshoprmi.resources;

import lk.ijse.jewelryshoprmi.entity.Customer;
import lk.ijse.jewelryshoprmi.entity.Jewelry;
import lk.ijse.jewelryshoprmi.entity.JewelryPurchaseDetail;
import lk.ijse.jewelryshoprmi.entity.Orders;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtill {
    private static SessionFactory sessionFactory=buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().loadProperties("db.properties").build();
        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Orders.class)
                .addAnnotatedClass(Jewelry.class)
                .addAnnotatedClass(JewelryPurchaseDetail.class)
                .buildMetadata();
        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
