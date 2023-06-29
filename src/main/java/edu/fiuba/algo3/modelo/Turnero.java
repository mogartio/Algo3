package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Interface.ControladorCompra;
import edu.fiuba.algo3.modelo.Interface.VisualizadorDeMapa;
import edu.fiuba.algo3.modelo.juego.Juego;

import java.lang.module.Configuration;

public class Turnero implements Jugable {

    int contadorTurnos;

    public Turnero() {
        contadorTurnos = 0;
    }

    public void finTurnoJugador(){
        jugarTurnoMaquina();

    }
    public void jugarTurnoMaquina() {
        if (!Juego.getInstance().finalizado()) {
            Juego.getInstance().jugarTurno(contadorTurnos);
            Juego.getInstance().notificar("Turno");
            ControladorCompra.getInstance().actualizarPanelTienda();
            contadorTurnos++;
        }
    }

}
