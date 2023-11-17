package todo.todoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LogInController {
    @FXML
    private TextField usernameTextField, passwordTextField;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToSignIn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignInScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDashboard(ActionEvent event) throws IOException {
        String username = "";
        String password = "";
        if (!usernameTextField.getText().isEmpty()) {
            username = usernameTextField.getText();
        }
        if (!passwordTextField.getText().isEmpty()) {
            password = passwordTextField.getText();
        }

        String found_username = mojafunkcja(username,password);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DashboardScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        DashboardController.print_username(found_username);
    }
}
