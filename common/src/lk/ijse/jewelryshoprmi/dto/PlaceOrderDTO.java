package lk.ijse.jewelryshoprmi.dto;

import java.util.ArrayList;
import java.util.List;

public class PlaceOrderDTO implements SuperDTO {
    private CustomerDTO customerDTO;
    private List<JewelryDTO> jewelryDTOS=new ArrayList<>();

    public PlaceOrderDTO() {
    }

    public PlaceOrderDTO(CustomerDTO customerDTO, List<JewelryDTO> jewelryDTOS) {
        this.customerDTO = customerDTO;
        this.jewelryDTOS = jewelryDTOS;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public List<JewelryDTO> getJewelryDTOS() {
        return jewelryDTOS;
    }

    public void setJewelryDTOS(List<JewelryDTO> jewelryDTOS) {
        this.jewelryDTOS = jewelryDTOS;
    }

    @Override
    public String toString() {
        return "PlaceOrderDTO{" +
                "customerDTO=" + customerDTO +
                ", jewelryDTOS=" + jewelryDTOS +
                '}';
    }
}
