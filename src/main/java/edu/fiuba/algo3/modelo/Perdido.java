package edu.fiuba.algo3.modelo;

public class Perdido implements EstadoJuego {

    public EstadoJuego introducirEnemigo(Enemigo enemigo) {
        return this;
    }
    public EstadoJuego actualizarSegunEstadoDeJugador(boolean estaVivo) {
        return this;
    }

    public void pasarTurno() {};
}
