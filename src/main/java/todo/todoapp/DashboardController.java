package todo.todoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public class DashboardController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button logout_Button, openPopupPersonalButton;
    @FXML
    private  Label display_username_Label;
    private static String username;
    @FXML
    public void switchToLogIn1(ActionEvent event) throws IOException {
        username = "";

        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LogInScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void user_dashboard(String send_username){
        System.out.println("wypisane" + send_username);
        username = send_username;
        display_username_Label.setText(username);
    }

    @FXML
    private void openPopupPersonal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PopupPersonal.fxml"));
        Parent root = loader.load();

        PopupPersonalController popupController = loader.getController();

        Optional<HashMap<String, Object>> map = MongoDB.get_single(username);
        popupController.user_info(map);

        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        popupStage.setScene(new Scene(root));
        popupStage.showAndWait();
    }

}
