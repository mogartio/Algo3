package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Ganado implements EstadoJuego {
    public EstadoJuego introducirEnemigo(Enemigo enemigo) {
        return this;
    }
    public EstadoJuego actualizarSegunEstadoDeJugador(boolean estaVivo) {
        return this;
    }
    public void pasarTurno() {};
}
