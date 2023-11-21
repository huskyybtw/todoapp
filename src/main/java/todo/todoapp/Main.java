package todo.todoapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import todo.todoapp.General.Assignment;
import todo.todoapp.Mongo.MongoAS;
import todo.todoapp.Mongo.MongoDB;

import java.io.IOException;
import java.util.Date;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        setup(stage);


        Date testdate = new Date();
        Assignment test = new Assignment("test","dwa","dwa",testdate);
        MongoAS.insert_one(test);
        MongoAS.get_single(test);

        stage.show();
    }

    public static void setup(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LogInScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("TODO APP SUPER MEGA");
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}