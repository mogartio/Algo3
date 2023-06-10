package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public interface EstadoJuego {

    EstadoJuego introducirEnemigo(Enemigo enemigo);

    boolean finalizado();
    EstadoJuego jugarTurno(boolean jugadorVivo, int numeroTurno);
}

