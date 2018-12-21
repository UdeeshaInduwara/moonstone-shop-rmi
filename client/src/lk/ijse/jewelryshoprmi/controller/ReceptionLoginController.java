package lk.ijse.jewelryshoprmi.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReceptionLoginController implements Initializable {
    private double xOffset=0;
    private double yOffset=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void logIn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/jewelryshoprmi/view/PlaceOrderForm.fxml"));
        Stage stage=new Stage();
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset=event.getSceneX();
                yOffset=event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX()-xOffset);
                stage.setY(event.getScreenY()-yOffset);
            }
        });
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

        Node source = (Node) actionEvent.getSource();
        Stage window = (Stage) source.getScene().getWindow();
        window.close();
    }

    public void closeWindow(ActionEvent actionEvent) {
        Platform.exit();
    }
}
