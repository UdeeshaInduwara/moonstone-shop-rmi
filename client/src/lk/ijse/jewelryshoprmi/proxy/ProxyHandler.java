package lk.ijse.jewelryshoprmi.proxy;

import lk.ijse.jewelryshoprmi.service.ServiceFactory;
import lk.ijse.jewelryshoprmi.service.SuperService;
import lk.ijse.jewelryshoprmi.service.custom.CustomerService;
import lk.ijse.jewelryshoprmi.service.custom.JewelryService;
import lk.ijse.jewelryshoprmi.service.custom.PlaceOrderService;

import java.rmi.Naming;

public class ProxyHandler implements ServiceFactory {
    private static ProxyHandler proxyHandler;
    private JewelryService jewelryService;
    private CustomerService customerService;
    private PlaceOrderService placeOrderService;

    public static ProxyHandler getInstace() throws Exception {
        if (proxyHandler == null) {
            proxyHandler = new ProxyHandler();
        }
        return proxyHandler;
    }
    public ProxyHandler() throws Exception{
        ServiceFactory serviceFactory = (ServiceFactory) Naming.lookup("rmi://localhost:5050/jsrmi");
        jewelryService = serviceFactory.getService(ServiceTypes.JEWELRY);
        customerService=serviceFactory.getService(ServiceTypes.CUSTOMER);
        placeOrderService=serviceFactory.getService(ServiceTypes.PLACEORDER);
    }

    @Override
    public <T extends SuperService> T getService(ServiceTypes types) throws Exception {
        switch (types){
            case JEWELRY: return (T) jewelryService;
            case CUSTOMER: return (T) customerService;
            case PLACEORDER: return (T) placeOrderService;
            default: return null;
        }
    }
}
