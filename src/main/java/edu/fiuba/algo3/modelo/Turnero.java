package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.Interface.ControladorCompra;
import edu.fiuba.algo3.modelo.juego.Juego;

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

            contadorTurnos++;
        }
    }

}
