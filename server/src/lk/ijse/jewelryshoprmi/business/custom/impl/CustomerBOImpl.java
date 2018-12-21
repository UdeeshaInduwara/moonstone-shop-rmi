package lk.ijse.jewelryshoprmi.business.custom.impl;

import lk.ijse.jewelryshoprmi.business.custom.CustomerBO;
import lk.ijse.jewelryshoprmi.dto.CustomerDTO;
import lk.ijse.jewelryshoprmi.entity.Customer;
import lk.ijse.jewelryshoprmi.repository.RepoFactory;
import lk.ijse.jewelryshoprmi.repository.custom.CustomerRepo;
import lk.ijse.jewelryshoprmi.resources.HibernateUtill;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerRepo customerRepo;
    public CustomerBOImpl() {
        customerRepo= RepoFactory.getInstance().getRepo(RepoFactory.RepoTypes.CUSTOMER);
    }

    @Override
    public boolean addCustomer(CustomerDTO dto) throws Exception {
        boolean response=false;
        try(Session session = HibernateUtill.getSessionFactory().openSession()){
            customerRepo.setSession(session);
            session.beginTransaction();

            response= customerRepo.add(new Customer(dto.getId(),dto.getName(),dto.getCountry(),dto.getPassposrtNo()));

            session.getTransaction().commit();
        }
        return response;
    }

    @Override
    public boolean deleteCustomer(CustomerDTO dto) throws Exception {
        boolean response=false;
        try(Session session = HibernateUtill.getSessionFactory().openSession()){
            customerRepo.setSession(session);
            session.beginTransaction();

            response= customerRepo.delete(new Customer(dto.getId(),dto.getName(),dto.getCountry(),dto.getPassposrtNo()));

            session.getTransaction().commit();
        }
        return response;
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        boolean response=false;
        try(Session session = HibernateUtill.getSessionFactory().openSession()){
            customerRepo.setSession(session);
            session.beginTransaction();

            response= customerRepo.update(new Customer(dto.getId(),dto.getName(),dto.getCountry(),dto.getPassposrtNo()));

            session.getTransaction().commit();
        }
        return response;
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        Customer response=null;
        try(Session session = HibernateUtill.getSessionFactory().openSession()){
            customerRepo.setSession(session);
            session.beginTransaction();

            response= customerRepo.search(id);

            session.getTransaction().commit();
        }
        return new CustomerDTO(response.getId(),response.getName(),response.getCountry(),response.getPassposrtNo());
    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws Exception {
        List<Customer> list=null;
        try(Session session = HibernateUtill.getSessionFactory().openSession()){
            customerRepo.setSession(session);
            session.beginTransaction();

            list = customerRepo.getAll();

            session.getTransaction().commit();
        }
        ArrayList<CustomerDTO> customerDTOS=new ArrayList<>();
        for (Customer response : list) {
            customerDTOS.add(new CustomerDTO(response.getId(),response.getName(),response.getCountry(),response.getPassposrtNo()));
        }
        return customerDTOS;
    }
}
