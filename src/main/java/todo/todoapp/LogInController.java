package todo.todoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LogInController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    private DashboardController dashboardController;
    private Stage stage;
    private Scene scene;


    @FXML
    public void switchToSignIn(ActionEvent event) throws IOException {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            SceneManager.SwitchScene(currentStage, "SignUpScene.fxml");
    }

    public void switchToDashboard(ActionEvent event) throws IOException {
        String username = "";
        String password = "";
        if (!usernameTextField.getText().isEmpty()) {
            username = usernameTextField.getText();
        }
        if (!passwordField.getText().isEmpty()) {
            password = passwordField.getText();
        }

        String found_username = MongoDB.check_user_password(username, password);

        if (found_username.isEmpty()) {
            System.out.println("ERROR PUSTY STRING");
        } else {

                SceneManager.SwitchToScene((Stage) ((Node) event.getSource()).getScene().getWindow(), "DashboardScene.fxml", controller -> {
                DashboardController dashboardController = (DashboardController) controller;
                dashboardController.user_dashboard(found_username);
            });

        }
    }
}
