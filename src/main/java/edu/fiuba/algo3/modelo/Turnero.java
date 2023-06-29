package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.juego.Juego;

public class Turnero implements Jugable {

    int contadorTurnos;

    public Turnero() {
        contadorTurnos = 0;
    }

    public void finTurnoJugador(){
        System.out.println("Turnero: Fin turno jugador n " + contadorTurnos );
        jugarTurnoMaquina();
    }
    public void jugarTurnoMaquina() {
        if (!juego.finalizado()) {
            juego.jugarTurno(contadorTurnos);
            juego.notificar();
            contadorTurnos++;
        }
    }

    /* public void proximoTurno() {
        ArrayList<Enemigo>
    }*/

}
