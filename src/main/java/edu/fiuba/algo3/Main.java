package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Interface.InicializadorVentana;
import edu.fiuba.algo3.modelo.Interface.VentanaDeJuego;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override

    public void start(Stage stage) throws Exception {
        VentanaDeJuego.setVentanaDelJuego(stage);
        Stage ventanaDeJuego = VentanaDeJuego.getInstance();

        ventanaDeJuego.setWidth(945);
        ventanaDeJuego.setHeight(957);
        ventanaDeJuego.setResizable(false);

        InicializadorVentana.start(ventanaDeJuego);
    }
}
