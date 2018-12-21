package lk.ijse.jewelryshoprmi.dto;

public class JewelryDTO implements SuperDTO {
    private String id;
    private String name;
    private String metal;
    private int carate;
    private double weight;
    private double size;
    private double price;

    public JewelryDTO() {
    }

    public JewelryDTO(String id, String name, String metal, int carate, double weight, double size, double price) {
        this.id = id;
        this.name = name;
        this.metal = metal;
        this.carate = carate;
        this.weight = weight;
        this.size = size;
        this.price = price;
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

    @Override
    public String toString() {
        return "JewelryDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", metal='" + metal + '\'' +
                ", carate=" + carate +
                ", weight=" + weight +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
