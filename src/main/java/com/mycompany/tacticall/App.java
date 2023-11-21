package com.mycompany.tacticall;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage currentStage;
    private static Scene scene;

    @Override
    public void start(Stage primaryStage) throws IOException {
        System.setProperty("prism.allowhidpi", "true");
        currentStage = primaryStage;
        scene = new Scene(loadFXML("primary"), 640, 480);
        Image icone = new Image(getClass().getResource("/com/mycompany/tacticall/Imagens/icone.png").toExternalForm());
        currentStage.getIcons().add(icone);
        currentStage.setTitle("TacticAll - Home");
        currentStage.setScene(scene);
        currentStage.setMaximized(true);
        currentStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static Stage getStage()
    {
        return currentStage;
    }

}