package lk.ijse.jewelryshoprmi.service.custom;

import lk.ijse.jewelryshoprmi.dto.JewelryDTO;
import lk.ijse.jewelryshoprmi.observer.Subject;
import lk.ijse.jewelryshoprmi.reservations.Reservations;
import lk.ijse.jewelryshoprmi.service.SuperService;

import java.util.List;

public interface JewelryService extends SuperService, Reservations, Subject {
    public boolean addJewelry(JewelryDTO dto)throws Exception;
    public boolean deleteJewelry(JewelryDTO dto)throws Exception;
    public boolean updateJewelry(JewelryDTO dto)throws Exception;
    public JewelryDTO searchJewelry(String id)throws Exception;
    public List<JewelryDTO> getAllJewelry()throws Exception;
}
