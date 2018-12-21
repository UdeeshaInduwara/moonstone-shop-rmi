package lk.ijse.jewelryshoprmi.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Orders {
    @Id
    private String id;

    private Date orderDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Customer customer;

    @OneToMany(mappedBy = "orders",cascade = CascadeType.PERSIST)
    private List<JewelryPurchaseDetail> jewelryPurchaseDetails=new ArrayList<>();

    public Orders() {
    }

    public Orders(String id, Date orderDate) {
        this.id = id;
        this.orderDate = orderDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<JewelryPurchaseDetail> getJewelryPurchaseDetails() {
        return jewelryPurchaseDetails;
    }

    public void setJewelryPurchaseDetails(List<JewelryPurchaseDetail> jewelryPurchaseDetails) {
        this.jewelryPurchaseDetails = jewelryPurchaseDetails;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id='" + id + '\'' +
                ", orderDate=" + orderDate +
                ", customer=" + customer +
                ", jewelryPurchaseDetails=" + jewelryPurchaseDetails +
                '}';
    }
}
