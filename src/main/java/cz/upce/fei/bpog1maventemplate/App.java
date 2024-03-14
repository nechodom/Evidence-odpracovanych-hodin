package cz.upce.fei.bpog1maventemplate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    // TODO rename the project
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // put name of the FXML file (without extension) below, not the controller name!!!
        scene = new Scene(loadFXML("FXMLDocument"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        // every .fxml file must be in the resources folder for the loader to find it
        // every .java file must be in the source packages (java folder in File explorer)
        // both .fxml files and .java files should follow the same package structure
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}