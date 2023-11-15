package todo.todoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class DashboardController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button r_login;
    @FXML
    private TextField name_TextField, surname_TextField, username_TextField;
    @FXML
    public void switchToLogIn1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LogInScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void readText(ActionEvent event){
        MongoDB.get_one("username", username_TextField.getText());




    }
}
