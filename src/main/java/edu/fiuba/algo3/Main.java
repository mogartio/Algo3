package edu.fiuba.algo3;

import edu.fiuba.algo3.Interface.InicializadorVentana;
import edu.fiuba.algo3.Interface.VentanaDeJuego;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override

    public void start(Stage stage) throws Exception {
        VentanaDeJuego.setVentanaDelJuego(stage);
        stage.setOnCloseRequest(WindowEvent ->{
            Platform.exit();
        });
        stage.setWidth(900);
        stage.setHeight(715);
        stage.setResizable(false);
        InicializadorVentana.start(stage);
    }
}
