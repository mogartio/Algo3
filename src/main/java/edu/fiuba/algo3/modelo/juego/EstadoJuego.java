package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.ObserverPropio.Emisor;

public interface EstadoJuego {

    EstadoJuego introducirEnemigo(Enemigo enemigo);
    EstadoJuego introducirDefensa(Defensa defensa);

    boolean finalizado();
    EstadoJuego jugarTurno(boolean jugadorVivo, int numeroTurno);

    void destruirDefensaMasAntigua();
}

