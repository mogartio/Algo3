package edu.fiuba.algo3.modelo.Interface;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BotonDeInicioDeJuego implements EventHandler<ActionEvent>{
    private Button miBoton;
    private Stage stage;

    public BotonDeInicioDeJuego(Button unBoton, Stage stage) {
        this.miBoton = unBoton;
        this.stage = stage;
    }
    public void handle(ActionEvent actionEvent) {
        // Create and configure the new scene
        StackPane newRoot = new StackPane();
        Button backButton = new Button("Go Back");

        newRoot.getChildren().add(backButton);
        Scene newScene = new Scene(newRoot, 400, 300);

        // Set the new scene on the primary stage
        stage.setScene(newScene);
    }


}
