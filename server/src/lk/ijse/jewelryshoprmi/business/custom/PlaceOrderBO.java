package lk.ijse.jewelryshoprmi.business.custom;

import lk.ijse.jewelryshoprmi.business.SuperBO;
import lk.ijse.jewelryshoprmi.dto.PlaceOrderDTO;

public interface PlaceOrderBO extends SuperBO {
    public boolean placeOrder(PlaceOrderDTO dto)throws Exception;
}
