package edu.fiuba.algo3.modelo.Interface;

import edu.fiuba.algo3.modelo.Jugable;
import edu.fiuba.algo3.modelo.Turnero;
import edu.fiuba.algo3.modelo.juego.Juego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BotonTurnero implements EventHandler<ActionEvent> {
    private Button miBoton;
    private Jugable jugable; // Tal vez deberia ser un "Jugable"

    public BotonTurnero(Button unBoton, Jugable jugable) {
        this.miBoton = unBoton;
        this.jugable = jugable;
    }

    public void handle(ActionEvent actionEvent) {
        jugable.finTurnoJugador();
    }
}
