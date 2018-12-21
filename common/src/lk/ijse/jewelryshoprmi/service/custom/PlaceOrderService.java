package lk.ijse.jewelryshoprmi.service.custom;

import lk.ijse.jewelryshoprmi.dto.PlaceOrderDTO;
import lk.ijse.jewelryshoprmi.observer.Subject;
import lk.ijse.jewelryshoprmi.reservations.Reservations;
import lk.ijse.jewelryshoprmi.service.SuperService;

public interface PlaceOrderService extends SuperService, Reservations, Subject {
    public boolean placeOrder(PlaceOrderDTO dto)throws Exception;
}
