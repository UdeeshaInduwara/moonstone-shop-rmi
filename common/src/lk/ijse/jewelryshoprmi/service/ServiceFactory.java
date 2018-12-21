package lk.ijse.jewelryshoprmi.service;

import java.rmi.Remote;

public interface ServiceFactory extends Remote {
    public enum ServiceTypes{
        PLACEORDER,JEWELRY,CUSTOMER
    }
    public <T extends SuperService>T getService(ServiceTypes types)throws Exception;
}
