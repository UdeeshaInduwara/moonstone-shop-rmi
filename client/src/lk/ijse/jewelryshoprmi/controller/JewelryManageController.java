package lk.ijse.jewelryshoprmi.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.jewelryshoprmi.controller.notification.Notification;
import lk.ijse.jewelryshoprmi.dto.JewelryDTO;
import lk.ijse.jewelryshoprmi.observer.Observer;
import lk.ijse.jewelryshoprmi.proxy.ProxyHandler;
import lk.ijse.jewelryshoprmi.service.ServiceFactory;
import lk.ijse.jewelryshoprmi.service.custom.JewelryService;
import lk.ijse.jewelryshoprmi.service.custom.PlaceOrderService;

import java.net.URL;
import java.rmi.server.UnicastRemoteObject;
import java.util.ResourceBundle;

public class JewelryManageController implements Initializable, Observer {
    public TableView<JewelryDTO> tblJewelry;
    public JFXTextField txtJewelryID;
    public JFXTextField txtName;
    public JFXTextField txtMetal;
    public JFXTextField txtCarat;
    public JFXTextField txtWeight;
    public JFXTextField txtSize;
    public JFXTextField txtPrice;

    private JewelryService jewelryService;
    private PlaceOrderService placeOrderService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            UnicastRemoteObject.exportObject(this,0);
            jewelryService = ProxyHandler.getInstace().getService(ServiceFactory.ServiceTypes.JEWELRY);
            jewelryService.register(this);

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
    }

    private void loadJewelryTbl() {
        try {
            tblJewelry.setItems(FXCollections.observableArrayList(jewelryService.getAllJewelry()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addJewelry(ActionEvent actionEvent) {
        String jewelryID = txtJewelryID.getText();
        String name = txtName.getText();
        String metal = txtMetal.getText();
        int carat = Integer.parseInt(txtCarat.getText());
        double weight = Double.parseDouble(txtWeight.getText());
        double size = Double.parseDouble(txtSize.getText());
        double price = Double.parseDouble(txtPrice.getText());
        try {
            boolean isOk = jewelryService.addJewelry(new JewelryDTO(jewelryID,name,metal,carat,weight,size,price));
            if (isOk){
                Notification.showConformationeMessage();
            }else {
                Notification.showFailureMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateJewelry(ActionEvent actionEvent) {
        String jewelryID = txtJewelryID.getText();
        String name = txtName.getText();
        String metal = txtMetal.getText();
        int carat = Integer.parseInt(txtCarat.getText());
        double weight = Double.parseDouble(txtWeight.getText());
        double size = Double.parseDouble(txtSize.getText());
        double price = Double.parseDouble(txtPrice.getText());
        try {
            boolean isOk = jewelryService.updateJewelry(new JewelryDTO(jewelryID,name,metal,carat,weight,size,price));
            if (isOk){
                Notification.showConformationeMessage();
            }else {
                Notification.showFailureMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteJewelry(ActionEvent actionEvent) {
        String jewelryID = txtJewelryID.getText();
        String name = txtName.getText();
        String metal = txtMetal.getText();
        int carat = Integer.parseInt(txtCarat.getText());
        double weight = Double.parseDouble(txtWeight.getText());
        double size = Double.parseDouble(txtSize.getText());
        double price = Double.parseDouble(txtPrice.getText());
        try {
            boolean isOk = jewelryService.deleteJewelry(new JewelryDTO(jewelryID,name,metal,carat,weight,size,price));
            if (isOk){
                Notification.showConformationeMessage();
            }else {
                Notification.showFailureMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectJewelryFromTbl(MouseEvent mouseEvent) {
        JewelryDTO selectedItem = tblJewelry.getSelectionModel().getSelectedItem();
        txtJewelryID.setText(selectedItem.getId());
        txtName.setText(selectedItem.getName());
        txtMetal.setText(selectedItem.getMetal());
        txtCarat.setText(String.valueOf(selectedItem.getCarate()));
        txtWeight.setText(String.valueOf(selectedItem.getWeight()));
        txtSize.setText(String.valueOf(selectedItem.getSize()));
        txtPrice.setText(String.valueOf(selectedItem.getPrice()));

        try {
            jewelryService.reserved(selectedItem.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String message) throws Exception {
        loadJewelryTbl();
    }
}
