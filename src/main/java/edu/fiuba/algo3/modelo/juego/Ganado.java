package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.ObserverPropio.Emisor;

public class Ganado implements EstadoJuego {
    public EstadoJuego introducirEnemigo(Enemigo enemigo) {
        return this;
    }

    public EstadoJuego introducirDefensa(Defensa defensa) { return this; }
    public boolean finalizado() {
        //emisor.notificarSubscriptores("log", "El jugador gana la partida");
        return true;
    }
    public EstadoJuego jugarTurno(boolean jugadorVivo, int numeroTurno) {
        return this;
    }
}
