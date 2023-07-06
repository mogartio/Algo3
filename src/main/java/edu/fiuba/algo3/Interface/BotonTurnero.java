package edu.fiuba.algo3.Interface;

import edu.fiuba.algo3.modelo.Jugable;
import edu.fiuba.algo3.modelo.Turnero;
import edu.fiuba.algo3.modelo.juego.Juego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BotonTurnero implements EventHandler<ActionEvent> {
    private final Jugable jugable;

    public BotonTurnero(Jugable jugable) {
        this.jugable = jugable;
    }

    public void handle(ActionEvent actionEvent) {
        jugable.finTurnoJugador();
        ControladorCompra.getInstance().actualizarPanelTienda();
    }
}
