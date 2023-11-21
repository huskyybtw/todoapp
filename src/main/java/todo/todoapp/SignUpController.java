package todo.todoapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import todo.todoapp.General.Person;
import todo.todoapp.Mongo.MongoDB;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class SignUpController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField username_TextField, name_TextField, surname_TextField, password_TextField, c_password_TextField;
    @FXML
    private Label info_Label, info_username_Label, info_name_Label, info_surname_Label, info_password_Label, info_c_password_Label;
    @FXML
    public void switchToLogIn(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LogInScene.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SignUP(ActionEvent event){
        String username = "", name = "", surname = "", password = "", c_password = "";

        if(username_TextField.getText().isEmpty()){
            info_username_Label.setText("Wpisz nazwe uzytkownika");
        }
        else{
            username = username_TextField.getText();
        }

        if(name_TextField.getText().isEmpty()){
            info_name_Label.setText("Wpisz imie");
        }
        else{
            name = username_TextField.getText();
        }

        if(surname_TextField.getText().isEmpty()){
            info_surname_Label.setText("Wpisz nazwisko");
        }
        else{
            surname = surname_TextField.getText();
        }

        if(password_TextField.getText().isEmpty()){
            info_password_Label.setText("Wpisz haslo");
        }
        else{
            password = password_TextField.getText();
        }

        if(c_password_TextField.getText().isEmpty()){
            info_c_password_Label.setText("Powtorz haslo");
        }
        else if(!c_password_TextField.getText().equals(password)){
            info_c_password_Label.setText("Hasla do siebie nie pasuja");
        }
        else{
            c_password = c_password_TextField.getText();
        }
        // UGLY WAY OF CHECKING IF EVERYTHING WAS FILLED IN
        if(password.equals(c_password)
                && !username.isEmpty()
                && !name.isEmpty()
                && !surname.isEmpty()
                && !password.isEmpty()) {

            Person person = new Person(username,password,name,surname);
            MongoDB.insert_one(person);
            info_Label.setText("Zarejestrowano :" + username);
        }
        else{
            info_Label.setText("Nie zarejestrowano prawidlowo");
        }
    }


}

