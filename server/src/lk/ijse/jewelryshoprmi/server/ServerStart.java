package lk.ijse.jewelryshoprmi.server;

import lk.ijse.jewelryshoprmi.resources.HibernateUtill;
import lk.ijse.jewelryshoprmi.service.impl.ServiceFactoryImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerStart {
    public static void main(String[] args) {
//        SessionFactory sessionFactory = HibernateUtill.getSessionFactory();
//        try(Session session=sessionFactory.openSession()) {
//            session.beginTransaction();
//
//            session.getTransaction().commit();
//        }
//        sessionFactory.close();
        try {
            Registry registry = LocateRegistry.createRegistry(5050);
            registry.rebind("jsrmi", ServiceFactoryImpl.getInstance());
            System.out.println("Server Started");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
