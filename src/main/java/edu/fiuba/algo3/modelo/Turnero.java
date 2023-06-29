package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.juego.Juego;

public class Turnero implements Jugable {

    private  Juego juego;
    int contadorTurnos;

    public Turnero(Juego juego) {
        this.juego = Juego.getInstance();
        contadorTurnos = 0;
    }

    public void finTurnoJugador(){
        jugarTurnoMaquina();
    }
    public void jugarTurnoMaquina() {
        if (!juego.finalizado()) {
            juego.jugarTurno(contadorTurnos);
            juego.notificar();
            contadorTurnos++;
        }
    }

    @Override
    public void notificar() {
        juego.notificar();
    }

    /* public void proximoTurno() {
        ArrayList<Enemigo>
    }*/

}
