package lk.ijse.jewelryshoprmi.controller.notification;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class Notification {
    public static void showFailureMessage(){
        Notifications notifications=Notifications.create()
                .title("Failed")
                .text("Failed")
//                .graphic(new ImageView(new Image("/lk/ijse/jewelryshop/assest/Cancel_40px.png")))
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.show();
    }
    public static void showConformationeMessage(){
        Notifications notifications=Notifications.create()
                .title("Conformation")
                .text("SuccessFull")
//                .graphic(new ImageView(new Image("/lk/ijse/jewelryshop/assest/Ok_40px.png")))
                .hideAfter(Duration.seconds(2))
                .position(Pos.CENTER);
        notifications.show();
    }

}
