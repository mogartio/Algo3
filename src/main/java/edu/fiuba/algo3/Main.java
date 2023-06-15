package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Interface.InicializadorVentana;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        InicializadorVentana.start(stage);
    }
}
