package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.Creador.CreadorDeJuego;
import edu.fiuba.algo3.modelo.Creador.CreadorDeMapa;
import edu.fiuba.algo3.modelo.Creador.CreadorEnemigos;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import edu.fiuba.algo3.modelo.juego.Juego;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;

public class BotonDeInicioDeJuego implements EventHandler<ActionEvent>{
    private final String nombreJugador;
    private Button miBoton;
    private Stage stage;

    public BotonDeInicioDeJuego(Button unBoton, String nombreJugador) {
        this.nombreJugador = nombreJugador;
        this.miBoton = unBoton;
    }

    public void handle(ActionEvent actionEvent) {
        // Create and configure the new scene
        System.out.println("¡ Me han clickeado !");

        //VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa(15);
        //CreadorDeMapa creadorDeMapa = new CreadorDeMapa("ArchivosJson/mapa.json",15, visualizadorDeMapa);

        try {

            CreadorDeJuego.crearJuego("ArchivosJson/enemigos.json", "ArchivosJson/mapa.json",15, nombreJugador);

        } catch (NoHayCamino | NoHayInicial ex) {
            System.out.println("sarasa MAPA"); // catchear correctemente
        }
    }


}