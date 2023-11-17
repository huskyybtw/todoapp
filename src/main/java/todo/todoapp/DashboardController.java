package todo.todoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class DashboardController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button logout_Button, openPopupPersonalButton;
    @FXML
    private  Label display_username_Label;

    private static String username; // username stores username that logged in
    @FXML
    public void switchToLogIn1(ActionEvent event) throws IOException {
        username = "";

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneManager.SwitchScene(currentStage, "LogInScene.fxml");
    }
    @FXML
    public void user_dashboard(String send_username){ // DISPLAY PROPER USER BASED ON LOGIN
        username = send_username; // username stores username that logged in
        display_username_Label.setText(username);
    }

    // DISPLAY POPUP WINDOW WITH PERSNOAL INFO
    @FXML
    private void openPopupPersonal(ActionEvent event) throws IOException {
        SceneManager.SwitchToScene((Stage) ((Node) event.getSource()).getScene().getWindow(), "DashboardScene.fxml", controller -> {
            PopupPersonalController popupController = (PopupPersonalController) controller;


            HashMap<String, Object> map = MongoDB.get_single(username); // FIND DATA IN DATABASE

            if (map.isEmpty()){ // IF RETURNED MAP WAS EMPTY PROMPT AN ERROR
                System.out.println("ERROR in DASHBOARDCONTROLER : MAP IS EMPTY");
            }
            else { // ELSE CONTINUE

                popupController.user_info(map);

                // SOME UGLY CODE (SHOWS POPUP WINDOW)
                Stage popupStage = new Stage();
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
                popupStage.setScene(new Scene(root));

                //STAGE SETUP LESS UGLY
                popupStage.setTitle(username + " personal info");
                popupStage.showAndWait();
            }
        });
    }
}
