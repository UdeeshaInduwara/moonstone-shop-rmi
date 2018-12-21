package lk.ijse.jewelryshoprmi.business.custom;

import lk.ijse.jewelryshoprmi.business.SuperBO;
import lk.ijse.jewelryshoprmi.dto.JewelryDTO;

import java.util.List;

public interface JewelryBO extends SuperBO {
    public boolean addJewelry(JewelryDTO dto)throws Exception;
    public boolean deleteJewelry(JewelryDTO dto)throws Exception;
    public boolean updateJewelry(JewelryDTO dto)throws Exception;
    public JewelryDTO searchJewelry(String id)throws Exception;
    public List<JewelryDTO> getAllJewelry()throws Exception;
}
