package lk.ijse.jewelryshoprmi.business.custom.impl;

import com.sun.xml.internal.ws.handler.HandlerException;
import lk.ijse.jewelryshoprmi.business.custom.PlaceOrderBO;
import lk.ijse.jewelryshoprmi.dto.CustomerDTO;
import lk.ijse.jewelryshoprmi.dto.JewelryDTO;
import lk.ijse.jewelryshoprmi.dto.PlaceOrderDTO;
import lk.ijse.jewelryshoprmi.entity.Customer;
import lk.ijse.jewelryshoprmi.entity.Jewelry;
import lk.ijse.jewelryshoprmi.entity.JewelryPurchaseDetail;
import lk.ijse.jewelryshoprmi.entity.Orders;
import lk.ijse.jewelryshoprmi.generator.IDGenerator;
import lk.ijse.jewelryshoprmi.resources.HibernateUtill;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Date;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    public PlaceOrderBOImpl() {
    }

    @Override
    public boolean placeOrder(PlaceOrderDTO dto) throws Exception {
        CustomerDTO customerDTO = dto.getCustomerDTO();
        if (customerDTO.getId()==null){
            String newID = IDGenerator.getNewID("Customer", "id", "C");
            customerDTO.setId(newID);
        }
        Customer customer=new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getCountry(),customerDTO.getPassposrtNo());

        Orders orders = new Orders(IDGenerator.getNewID("Orders", "id", "O"),new Date());
        try(Session session = HibernateUtill.getSessionFactory().openSession()){
            session.beginTransaction();

            Customer newCustomer = session.get(Customer.class, customer.getId());
            if (newCustomer==null){
                newCustomer=customer;
            }

            orders.setCustomer(newCustomer);
            ArrayList<Orders> ordersArrayList = new ArrayList<>();
            ordersArrayList.add(orders);
            newCustomer.setOrders(ordersArrayList);

            ArrayList<JewelryPurchaseDetail> jpdArrayList=new ArrayList<>();
            for (JewelryDTO d : dto.getJewelryDTOS()) {
                JewelryPurchaseDetail jpd = new JewelryPurchaseDetail(d.getId(), d.getName(), d.getMetal(), d.getCarate(), d.getWeight(), d.getSize(), d.getPrice());
                jpd.setOrders(orders);
                jpdArrayList.add(jpd);

                Jewelry jewelry = session.get(Jewelry.class, d.getId());
                session.remove(jewelry);
            }
            orders.setJewelryPurchaseDetails(jpdArrayList);
            session.persist(newCustomer);

            session.getTransaction().commit();
        }catch (HandlerException e){
            return false;
        }
        return true;
    }
}
