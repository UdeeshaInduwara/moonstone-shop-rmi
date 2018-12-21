package lk.ijse.jewelryshoprmi.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    private String id;

    private String name;
    private String country;
    private String passposrtNo;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST)
    private List<Orders> orders=new ArrayList<>();

    public Customer() {
    }

    public Customer(String id, String name, String country, String passposrtNo) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.passposrtNo = passposrtNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassposrtNo() {
        return passposrtNo;
    }

    public void setPassposrtNo(String passposrtNo) {
        this.passposrtNo = passposrtNo;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", passposrtNo='" + passposrtNo + '\'' +
                ", orders=" + orders +
                '}';
    }
}
