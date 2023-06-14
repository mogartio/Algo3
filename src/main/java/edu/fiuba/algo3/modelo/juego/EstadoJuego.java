package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Observer.Emisor;

public interface EstadoJuego {

    EstadoJuego introducirEnemigo(Enemigo enemigo);
    EstadoJuego introducirDefensa(Defensa defensa);

    boolean finalizado(Emisor emisor);
    EstadoJuego jugarTurno(boolean jugadorVivo, int numeroTurno, Emisor emisor);
}

