package edu.fiuba.algo3.Interface;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InicializadorVentana {
    public static void start(Stage stage) throws Exception {

        stage.setTitle("TowerDefense: El juego!");

        //se crea la escena inicial
        BorderPane layout = new BorderPane();
        Scene scene = new Scene(layout);

        stage.setScene(scene);

        stage.show();

        InicializadorPantallaInicio.start(layout);
    }
}
