package edu.fiuba.algo3.Interface;

import edu.fiuba.algo3.CreadorDeJuego;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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

        //VisualizadorDeMapa visualizadorDeMapa = new VisualizadorDeMapa(15);
        //CreadorDeMapa creadorDeMapa = new CreadorDeMapa("ArchivosJson/mapa.json",15, visualizadorDeMapa);

        try {

            CreadorDeJuego.crearJuego("ArchivosJson/enemigos.json", "ArchivosJson/mapa.json",15, nombreJugador);
            VisualizadorLogger.getInstance().InicializarPanelLogger();

        } catch (NoHayCamino | NoHayInicial ex) {
            System.out.println("No se encontro algun json");
        }
    }


}