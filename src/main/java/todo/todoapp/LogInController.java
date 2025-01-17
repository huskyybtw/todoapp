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
import todo.todoapp.Enums.Role;
import todo.todoapp.General.Person;
import todo.todoapp.Mongo.MongoDB;

import java.io.IOException;
import java.util.Objects;

public class LogInController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;

    private Stage stage;
    private Scene scene;

    @FXML
    public void switchToSignIn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SignUpScene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToDashboard(ActionEvent event) throws IOException {
        String username = "";
        String password = "";
        if (!usernameTextField.getText().isEmpty()) {
            username = usernameTextField.getText();
        }
        if (!passwordField.getText().isEmpty()) {
            password = passwordField.getText();
        }

        String found_username = MongoDB.check_user_password(username,password);

        if(found_username.isEmpty()){
            System.out.println("ERROR PUSTY STRING");
        }
        // IF LOGIN WAS SUCCESSFUL
        // DEPENDING ON WHO LOGGED IN CHOOSE DASHBOARD
        else {
            Person loginPerson = MongoDB.getSingle(found_username);
            Role role = loginPerson.getRole();

            try {
                switch (role) {
                    case MANAGER: {
                        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("DashboardManageScene.fxml")));
                        Parent root = loader.load();
                        DashboardManager dashboardManager = loader.getController();
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        dashboardManager.initialize(loginPerson);

                        break;
                    }
                    case WORKER: {
                        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("DashboardWorkerScene.fxml")));
                        Parent root = loader.load();
                        DashboardWorker dashboardWorker = loader.getController();
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        dashboardWorker.initialize(loginPerson);

                        break;
                    }
                    case UNASSIGNED: {
                        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("DashboardUnassignedScene.fxml")));
                        Parent root = loader.load();
                        DashboardUnassigned dashboardUnassigned = loader.getController();
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        dashboardUnassigned.initialize(loginPerson);

                        break;
                    }
                }
            }
            catch (Exception e) {
                System.out.println("ERROR :"+ e.getMessage());
            }
        }
    }
}
