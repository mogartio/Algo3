package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public interface EstadoJuego {

    public EstadoJuego introducirEnemigo(Enemigo enemigo);

    public EstadoJuego actualizarSegunEstadoDeJugador(boolean jugadorVivo);

    void pasarTurno();
}
