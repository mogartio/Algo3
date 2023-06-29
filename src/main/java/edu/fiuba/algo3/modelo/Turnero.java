package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Interface.VisualizadorDeMapa;
import edu.fiuba.algo3.modelo.juego.Juego;

public class Turnero implements Jugable {

    int contadorTurnos;
    VisualizadorDeMapa visualizadorDeMapa;

    public Turnero(VisualizadorDeMapa visualizadorDeMapa) {
        this.visualizadorDeMapa = visualizadorDeMapa;
        contadorTurnos = 0;
    }

    public void finTurnoJugador(){
        System.out.println("Turnero: Fin turno jugador n " + contadorTurnos );
        jugarTurnoMaquina();

    }
    public void jugarTurnoMaquina() {
        if (!Juego.getInstance().finalizado()) {
            Juego.getInstance().jugarTurno(contadorTurnos);
            Juego.getInstance().notificar();
            System.out.println();
            contadorTurnos++;
        }
    }

    /* public void proximoTurno() {
        ArrayList<Enemigo>
    }*/

}
