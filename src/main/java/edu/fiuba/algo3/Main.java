package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.Creador.CreadorDeJuego;
import edu.fiuba.algo3.modelo.Creador.CreadorDeMapa;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import edu.fiuba.algo3.modelo.Interface.InicializadorVentana;
import edu.fiuba.algo3.modelo.Interface.VentanaDeJuego;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
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
        VentanaDeJuego.setVentanaDelJuego(stage);
        Stage ventanaDeJuego =VentanaDeJuego.getIntance();

        ventanaDeJuego.setWidth(945);
        ventanaDeJuego.setHeight(957);
        ventanaDeJuego.setResizable(false);

        InicializadorVentana.start(ventanaDeJuego);
        return;
    }
}
