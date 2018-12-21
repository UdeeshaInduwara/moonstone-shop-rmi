package lk.ijse.jewelryshoprmi.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {
    public Text lblDashboard;
    public Text lblJewelry;
    public Text lblCustomers;
    public AnchorPane pnlHome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/jewelryshoprmi/view/DashboardForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pnlHome.getChildren().setAll(pane);
        setColors(1);
    }

    public void loadDashboard(MouseEvent mouseEvent) {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/jewelryshoprmi/view/DashboardForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pnlHome.getChildren().setAll(pane);
        setColors(1);
    }

    public void loadJewelry(MouseEvent mouseEvent) {
        AnchorPane pane= null;
        try {
            pane = FXMLLoader.load(this.getClass().getResource("/lk/ijse/jewelryshoprmi/view/JewelryManageForm.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pnlHome.getChildren().setAll(pane);
        setColors(2);
    }

    public void loadCustomers(MouseEvent mouseEvent) {

        setColors(3);
    }

    public void closeWindow(ActionEvent actionEvent) {
        Platform.exit();
    }

    private void setColors(int num){
        switch (num){
            case 1:
                lblDashboard.setStyle("-fx-fill: #078d25");
                lblJewelry.setStyle("-fx-fill: white");
                lblCustomers.setStyle("-fx-fill: white");
                break;
            case 2:
                lblJewelry.setStyle("-fx-fill: #078d25");
                lblDashboard.setStyle("-fx-fill: white");
                lblCustomers.setStyle("-fx-fill: white");
                break;
            case 3:
                lblCustomers.setStyle("-fx-fill: #078d25");
                lblJewelry.setStyle("-fx-fill: white");
                lblDashboard.setStyle("-fx-fill: white");
                break;
        }
    }
}
