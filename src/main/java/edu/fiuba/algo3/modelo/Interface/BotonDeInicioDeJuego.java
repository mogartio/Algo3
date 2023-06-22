package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.Creador.CreadorDeJuego;
import edu.fiuba.algo3.modelo.Excepciones.NoHayCamino;
import edu.fiuba.algo3.modelo.Excepciones.NoHayInicial;
import edu.fiuba.algo3.modelo.juego.Juego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BotonDeInicioDeJuego implements EventHandler<ActionEvent>{
    private Button miBoton;
    private Stage stage;

    public BotonDeInicioDeJuego(Button unBoton) {
        this.miBoton = unBoton;
    }

    public void handle(ActionEvent actionEvent) {
        // Create and configure the new scene
        System.out.println("¡ Me han clickeado !");
        try {
            Juego juego = CreadorDeJuego.crearJuego("ArchivosJson/enemigos.json", "ArchivosJson/mapa.json",15);
        } catch (NoHayCamino | NoHayInicial ex) {
            System.out.println("sarasa MAPA");
        }
    }


}
