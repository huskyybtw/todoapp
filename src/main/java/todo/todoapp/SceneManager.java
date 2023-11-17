package todo.todoapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneManager {

    public interface ControllerSetupCallback {
        void setupController(Object controller);
    }

    public static void SwitchScene(Stage stage, String scenePath) {
        SwitchToScene(stage, scenePath, null);
    }

    public static void SwitchToScene(Stage stage, String scenePath, ControllerSetupCallback setupCallback) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(SceneManager.class.getResource(scenePath)));
            Scene newScene = new Scene(loader.load());
            stage.setScene(newScene);
            stage.show();

            if (setupCallback != null) {
                setupCallback.setupController(loader.getController());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}