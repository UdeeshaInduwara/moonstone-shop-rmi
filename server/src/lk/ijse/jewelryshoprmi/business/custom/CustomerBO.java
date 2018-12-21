package lk.ijse.jewelryshoprmi.business.custom;

import lk.ijse.jewelryshoprmi.business.SuperBO;
import lk.ijse.jewelryshoprmi.dto.CustomerDTO;

import java.util.List;

public interface CustomerBO extends SuperBO {
    public boolean addCustomer(CustomerDTO dto)throws Exception;
    public boolean deleteCustomer(CustomerDTO dto)throws Exception;
    public boolean updateCustomer(CustomerDTO dto)throws Exception;
    public CustomerDTO searchCustomer(String id)throws Exception;
    public List<CustomerDTO> getAllCustomers()throws Exception;
}
