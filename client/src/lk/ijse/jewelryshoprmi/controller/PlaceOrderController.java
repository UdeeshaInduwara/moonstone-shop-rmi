package lk.ijse.jewelryshoprmi.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.jewelryshoprmi.controller.notification.Notification;
import lk.ijse.jewelryshoprmi.dto.CustomerDTO;
import lk.ijse.jewelryshoprmi.dto.JewelryDTO;
import lk.ijse.jewelryshoprmi.dto.PlaceOrderDTO;
import lk.ijse.jewelryshoprmi.observer.Observer;
import lk.ijse.jewelryshoprmi.proxy.ProxyHandler;
import lk.ijse.jewelryshoprmi.service.ServiceFactory;
import lk.ijse.jewelryshoprmi.service.custom.CustomerService;
import lk.ijse.jewelryshoprmi.service.custom.JewelryService;
import lk.ijse.jewelryshoprmi.service.custom.PlaceOrderService;

import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlaceOrderController implements Initializable, Observer {
    public TableView<JewelryDTO> tblJewelry;
    public TableView<CustomerDTO> tblCustomers;
    public TableView<JewelryDTO> tblSelectedJewelry;
    public JFXTextField txtCustName;
    public JFXTextField txtCountry;
    public JFXTextField txtPassportNo;

    private ArrayList<JewelryDTO> selectedJewelryDtos=new ArrayList<>();
    private String txtCustId=null;
    private JewelryService jewelryService;
    private CustomerService customerService;
    private PlaceOrderService placeOrderService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            UnicastRemoteObject.exportObject(this,0);

            jewelryService = ProxyHandler.getInstace().getService(ServiceFactory.ServiceTypes.JEWELRY);
            jewelryService.register(this);

            customerService=ProxyHandler.getInstace().getService(ServiceFactory.ServiceTypes.CUSTOMER);
            customerService.register(this);

            placeOrderService=ProxyHandler.getInstace().getService(ServiceFactory.ServiceTypes.PLACEORDER);
            placeOrderService.register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tblJewelry.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblJewelry.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblJewelry.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("metal"));
        tblJewelry.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("carate"));
        tblJewelry.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("weight"));
        tblJewelry.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("size"));
        tblJewelry.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("price"));
        loadJewelryTbl();

        tblSelectedJewelry.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblSelectedJewelry.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblSelectedJewelry.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("price"));

        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("country"));
        tblCustomers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("passposrtNo"));
        loadCustomerTbl();
    }

    private void loadCustomerTbl() {
        try {
            tblCustomers.setItems(FXCollections.observableArrayList(customerService.getAllCustomers()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadJewelryTbl() {
        try {
            tblJewelry.setItems(FXCollections.observableArrayList(jewelryService.getAllJewelry()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectJewelry(MouseEvent mouseEvent) {
        try {
            JewelryDTO selectedItem = tblJewelry.getSelectionModel().getSelectedItem();
            if (!selectedJewelryDtos.contains(selectedItem)){
                boolean isReserved = jewelryService.reserved(selectedItem.getId());
                if (isReserved){
                    selectedJewelryDtos.add(selectedItem);
                    loadSelectedJewelryTbl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSelectedJewelryTbl() {
        try {
            tblSelectedJewelry.setItems(FXCollections.observableArrayList(selectedJewelryDtos));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveOrder(ActionEvent actionEvent) {
        String custId=txtCustId;
        String custName = txtCustName.getText();
        String country = txtCountry.getText();
        String passportNo = txtPassportNo.getText();
        CustomerDTO customerDTO = new CustomerDTO(custId, custName, country, passportNo);

        try {
            boolean isOk = placeOrderService.placeOrder(new PlaceOrderDTO(customerDTO, selectedJewelryDtos));
            if (isOk){
                selectedJewelryDtos.clear();
                txtCustId=null;
                txtCustName.clear();
                txtCountry.clear();
                txtPassportNo.clear();
                Notification.showConformationeMessage();
            }else {
                Notification.showFailureMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectCustomer(MouseEvent mouseEvent) {
        CustomerDTO selectedItem = tblCustomers.getSelectionModel().getSelectedItem();
        txtCustId=selectedItem.getId();
        txtCustName.setText(selectedItem.getName());
        txtCountry.setText(selectedItem.getCountry());
        txtPassportNo.setText(selectedItem.getPassposrtNo());
    }

    public void removeSelectedJewelry(MouseEvent mouseEvent) {
        JewelryDTO selectedItem = tblSelectedJewelry.getSelectionModel().getSelectedItem();
        selectedJewelryDtos.remove(selectedItem);
        loadSelectedJewelryTbl();

        try {
            jewelryService.released(selectedItem.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String message) throws Exception {
        loadJewelryTbl();
        loadCustomerTbl();
        loadSelectedJewelryTbl();
    }

    public void closeWindow(ActionEvent actionEvent) {
        Platform.exit();
    }
}
