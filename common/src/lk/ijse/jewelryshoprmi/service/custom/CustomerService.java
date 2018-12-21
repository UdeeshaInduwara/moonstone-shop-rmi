package lk.ijse.jewelryshoprmi.service.custom;

import lk.ijse.jewelryshoprmi.dto.CustomerDTO;
import lk.ijse.jewelryshoprmi.observer.Subject;
import lk.ijse.jewelryshoprmi.reservations.Reservations;
import lk.ijse.jewelryshoprmi.service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService, Subject, Reservations {
    public boolean addCustomer(CustomerDTO dto)throws Exception;
    public boolean deleteCustomer(CustomerDTO dto)throws Exception;
    public boolean updateCustomer(CustomerDTO dto)throws Exception;
    public CustomerDTO searchCustomer(String id)throws Exception;
    public List<CustomerDTO> getAllCustomers()throws Exception;
}
