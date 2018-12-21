package lk.ijse.jewelryshoprmi.business;

import lk.ijse.jewelryshoprmi.business.custom.impl.CustomerBOImpl;
import lk.ijse.jewelryshoprmi.business.custom.impl.JewelryBOImpl;
import lk.ijse.jewelryshoprmi.business.custom.impl.PlaceOrderBOImpl;

public class BOFactory {
    public enum BOTypes{
        JEWELRY,CUSTOMER,PLACEORDER
    }
    private static BOFactory boFactory;
    public static BOFactory getInstance(){
        if(boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }
    private BOFactory(){
    }
    public <T extends SuperBO> T getBO(BOTypes boType){
        switch(boType){
            case JEWELRY: return (T) new JewelryBOImpl();
            case CUSTOMER: return (T) new CustomerBOImpl();
            case PLACEORDER: return (T) new PlaceOrderBOImpl();
            default: return null;
        }
    }
}
