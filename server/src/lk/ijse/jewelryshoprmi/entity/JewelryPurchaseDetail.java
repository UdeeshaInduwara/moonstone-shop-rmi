package lk.ijse.jewelryshoprmi.entity;

import javax.persistence.*;

@Entity
public class JewelryPurchaseDetail {
    @Id
    @GeneratedValue
    private int id;
    private String jewid;
    private String name;
    private String metal;
    private int carate;
    private double weight;
    private double size;
    private double price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Orders orders;

    public JewelryPurchaseDetail() {
    }

    public JewelryPurchaseDetail(String jewid, String name, String metal, int carate, double weight, double size, double price) {
        this.jewid = jewid;
        this.name = name;
        this.metal = metal;
        this.carate = carate;
        this.weight = weight;
        this.size = size;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJewid() {
        return jewid;
    }

    public void setJewid(String jewid) {
        this.jewid = jewid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public int getCarate() {
        return carate;
    }

    public void setCarate(int carate) {
        this.carate = carate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "JewelryPurchaseDetail{" +
                "id='" + id + '\'' +
                ", jewid='" + jewid + '\'' +
                ", name='" + name + '\'' +
                ", metal='" + metal + '\'' +
                ", carate=" + carate +
                ", weight=" + weight +
                ", size=" + size +
                ", price=" + price +
                ", orders=" + orders +
                '}';
    }
}
