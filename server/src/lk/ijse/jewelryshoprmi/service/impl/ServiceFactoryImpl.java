package lk.ijse.jewelryshoprmi.service.impl;

import lk.ijse.jewelryshoprmi.service.ServiceFactory;
import lk.ijse.jewelryshoprmi.service.SuperService;
import lk.ijse.jewelryshoprmi.service.custom.impl.CustomerServiceImpl;
import lk.ijse.jewelryshoprmi.service.custom.impl.JewelryServiceImpl;
import lk.ijse.jewelryshoprmi.service.custom.impl.PlaceOrderServiceImpl;

import java.rmi.server.UnicastRemoteObject;

public class ServiceFactoryImpl extends UnicastRemoteObject implements ServiceFactory {
    public ServiceFactoryImpl() throws Exception {
    }

    private static ServiceFactoryImpl serviceFactory;

    public static ServiceFactoryImpl getInstance() throws Exception {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactoryImpl();
        }
        return serviceFactory;
    }

    @Override
    public <T extends SuperService> T getService(ServiceTypes types) throws Exception {
        switch (types){
            case JEWELRY: return (T) new JewelryServiceImpl();
            case CUSTOMER: return (T) new CustomerServiceImpl();
            case PLACEORDER: return (T) new PlaceOrderServiceImpl();
            default: return null;
        }
    }
}
