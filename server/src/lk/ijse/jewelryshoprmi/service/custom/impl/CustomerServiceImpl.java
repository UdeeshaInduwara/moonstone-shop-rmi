package lk.ijse.jewelryshoprmi.service.custom.impl;

import lk.ijse.jewelryshoprmi.business.BOFactory;
import lk.ijse.jewelryshoprmi.business.custom.CustomerBO;
import lk.ijse.jewelryshoprmi.dto.CustomerDTO;
import lk.ijse.jewelryshoprmi.observer.Observer;
import lk.ijse.jewelryshoprmi.reservation.impl.ReservationsImpl;
import lk.ijse.jewelryshoprmi.service.custom.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl extends UnicastRemoteObject implements CustomerService {
    private static ArrayList<Observer> customerObservers = new ArrayList<>();
    private static ReservationsImpl<CustomerService> customerReservations = new ReservationsImpl<>();

    private CustomerBO customerBO;
    public CustomerServiceImpl() throws RemoteException {
        customerBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    }

    @Override
    public boolean addCustomer(CustomerDTO dto) throws Exception {
        boolean isAdded = customerBO.addCustomer(dto);
        if (isAdded){
            notifyAllObservers("add");
        }
        return isAdded;
    }

    @Override
    public boolean deleteCustomer(CustomerDTO dto) throws Exception {
        if (customerReservations.reserve(dto.getId(),this,true)){
            boolean isDeleted = customerBO.deleteCustomer(dto);
            if (isDeleted){
                notifyAllObservers("delete");
                if(customerReservations.checkState(dto.getId(),this)){
                    boolean isReleased = customerReservations.release(dto.getId(), this);
                    if (isReleased){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        if (customerReservations.reserve(dto.getId(),this,true)){
            boolean isUpdated = customerBO.updateCustomer(dto);
            if (isUpdated){
                notifyAllObservers("update");
                if(customerReservations.checkState(dto.getId(),this)){
                    boolean isReleased = customerReservations.release(dto.getId(), this);
                    if (isReleased){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        return customerBO.searchCustomer(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws Exception {
        return customerBO.getAllCustomers();
    }

    @Override
    public void register(Observer observer) throws Exception {
        customerObservers.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {
        customerObservers.remove(observer);
    }

    @Override
    public void notifyAllObservers(String message) throws Exception {
        for (Observer val : customerObservers) {
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
        return customerReservations.reserve(id,this,true);
    }

    @Override
    public boolean released(Object id) throws Exception {
        return customerReservations.release(id,this);
    }
}
