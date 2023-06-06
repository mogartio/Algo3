package edu.fiuba.algo3.modelo;

public class Perdido implements EstadoJuego {

    public EstadoJuego introducirEnemigo(Enemigo enemigo) {
        return this;
    }
    public EstadoJuego actualizarConVidaDeJugador(int cantVida) {
        return this;
    }

    public void pasarTurno() {};
}
