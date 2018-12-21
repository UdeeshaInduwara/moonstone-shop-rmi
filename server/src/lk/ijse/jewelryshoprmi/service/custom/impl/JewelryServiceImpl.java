package lk.ijse.jewelryshoprmi.service.custom.impl;

import lk.ijse.jewelryshoprmi.business.BOFactory;
import lk.ijse.jewelryshoprmi.business.custom.JewelryBO;
import lk.ijse.jewelryshoprmi.dto.JewelryDTO;
import lk.ijse.jewelryshoprmi.observer.Observer;
import lk.ijse.jewelryshoprmi.reservation.impl.ReservationsImpl;
import lk.ijse.jewelryshoprmi.service.custom.JewelryService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class JewelryServiceImpl extends UnicastRemoteObject implements JewelryService {

    private static ArrayList<Observer> jewelryObservers = new ArrayList<>();
    private static ReservationsImpl<JewelryService> jewelryReservations = new ReservationsImpl<>();

    private JewelryBO jewelryBO;
    public JewelryServiceImpl() throws RemoteException {
        jewelryBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.JEWELRY);
    }

    @Override
    public boolean addJewelry(JewelryDTO dto) throws Exception {
        boolean isAdded = jewelryBO.addJewelry(dto);
        if (isAdded){
            notifyAllObservers("add");
        }
        return isAdded;
    }

    @Override
    public boolean deleteJewelry(JewelryDTO dto) throws Exception {
        if (jewelryReservations.reserve(dto.getId(),this,true)){
            boolean isDeleted = jewelryBO.deleteJewelry(dto);
            if (isDeleted){
                notifyAllObservers("delete");
                if(jewelryReservations.checkState(dto.getId(),this)){
                    boolean isReleased = jewelryReservations.release(dto.getId(), this);
                    if (isReleased){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateJewelry(JewelryDTO dto) throws Exception {
        if (jewelryReservations.reserve(dto.getId(),this,true)){
            boolean isUpdated = jewelryBO.updateJewelry(dto);
            if (isUpdated){
                notifyAllObservers("update");
                if(jewelryReservations.checkState(dto.getId(),this)){
                    boolean isReleased = jewelryReservations.release(dto.getId(), this);
                    if (isReleased){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public JewelryDTO searchJewelry(String id) throws Exception {
        return jewelryBO.searchJewelry(id);
    }

    @Override
    public List<JewelryDTO> getAllJewelry() throws Exception {
        return jewelryBO.getAllJewelry();
    }

    @Override
    public boolean reserved(Object id) throws Exception {
        return jewelryReservations.reserve(id,this,true);
    }

    @Override
    public boolean released(Object id) throws Exception {
        return jewelryReservations.release(id,this);
    }

    @Override
    public void register(Observer observer) throws Exception {
        jewelryObservers.add(observer);
    }

    @Override
    public void unregister(Observer observer) throws Exception {
        jewelryObservers.remove(observer);
    }

    @Override
    public void notifyAllObservers(String message) throws Exception {
        for (Observer val : jewelryObservers) {
            new Thread(() -> {
                try {
                    val.update(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
