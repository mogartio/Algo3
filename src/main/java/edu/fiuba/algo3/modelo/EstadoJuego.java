package edu.fiuba.algo3.modelo;

public interface EstadoJuego {

    public EstadoJuego introducirEnemigo(Enemigo enemigo);

    public EstadoJuego actualizarSegunEstadoDeJugador(boolean estaVivo);

    void pasarTurno();
}
