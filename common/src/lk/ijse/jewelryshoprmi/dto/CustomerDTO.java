package lk.ijse.jewelryshoprmi.dto;

public class CustomerDTO implements SuperDTO{
    private String id;
    private String name;
    private String country;
    private String passposrtNo;

    public CustomerDTO() {
    }

    public CustomerDTO(String id, String name, String country, String passposrtNo) {
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

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", passposrtNo='" + passposrtNo + '\'' +
                '}';
    }
}
