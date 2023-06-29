package edu.fiuba.algo3.modelo.Interface;

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
    private Button miBoton;
    private Stage stage;

    public BotonDeInicioDeJuego(Button unBoton) {
        this.miBoton = unBoton;
    }

    public void handle(ActionEvent actionEvent) {
        // Create and configure the new scene
        System.out.println("¡ Me han clickeado !");

        VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa(15);
        CreadorDeMapa creadorDeMapa = new CreadorDeMapa("ArchivosJson/mapa.json",15, visualizadorDeMapa);

        try {

            CreadorDeJuego.crearJuego("ArchivosJson/enemigos.json", "ArchivosJson/mapa.json",15); //falta agregarle el nombre del jugador
            //CreadorDeJuego.crearJuego("ArchivosJson/enemigos.json", "ArchivosJson/mapa.json",15, nombreJugador);
            /*Mapa mapa = creadorDeMapa.crearMapa();

            Mapa mapa = creadorDeMapa.crearMapa();

            CreadorEnemigos creadorEnemigos = new CreadorEnemigos();
            LinkedList<ArrayList<Enemigo>> enemigos = creadorEnemigos.crearEnemigosDeNivel("ArchivosJson/enemigos.json");

            Juego juego = Juego.getInstance();
            juego.reestablecerJuego();
            juego.setMapa(mapa);
            juego.setOleadasDelNivel(enemigos);*/

        } catch (NoHayCamino | NoHayInicial ex) {
            System.out.println("sarasa MAPA"); // catchear correctemente
        }
    }


}