package lk.ijse.jewelryshoprmi.service.custom.impl;

import lk.ijse.jewelryshoprmi.business.BOFactory;
import lk.ijse.jewelryshoprmi.business.custom.PlaceOrderBO;
import lk.ijse.jewelryshoprmi.dto.JewelryDTO;
import lk.ijse.jewelryshoprmi.dto.PlaceOrderDTO;
import lk.ijse.jewelryshoprmi.observer.Observer;
import lk.ijse.jewelryshoprmi.reservation.impl.ReservationsImpl;
import lk.ijse.jewelryshoprmi.service.custom.PlaceOrderService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class PlaceOrderServiceImpl extends UnicastRemoteObject implements PlaceOrderService {
    private static ArrayList<Observer> placeOrderObservers = new ArrayList<>();
    private static ReservationsImpl<PlaceOrderService> placeOrderReservations = new ReservationsImpl<>();

    private PlaceOrderBO placeOrderBO;
    public PlaceOrderServiceImpl() throws RemoteException {
        placeOrderBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.PLACEORDER);
    }

    @Override
    public boolean placeOrder(PlaceOrderDTO dto) throws Exception {
        boolean isSaved = placeOrderBO.placeOrder(dto);
        if (isSaved){
            notifyAllObservers("add");
        }
        return isSaved;
    }

    @Override
    public void register(Observer observer) throws Exception {
        placeOrderObservers.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {
        placeOrderObservers.remove(observer);
    }

    @Override
    public void notifyAllObservers(String message) throws Exception {
        for (Observer val : placeOrderObservers) {
            new Thread(() -> {
                try {
                    val.update(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    @Override
    public boolean reserved(Object id) throws Exception {
        return placeOrderReservations.reserve(id,this,true);
    }

    @Override
    public boolean released(Object id) throws Exception {
        return placeOrderReservations.release(id,this);
    }
}
