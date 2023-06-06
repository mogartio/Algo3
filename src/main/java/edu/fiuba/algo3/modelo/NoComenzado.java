package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class NoComenzado implements EstadoJuego {
    public EstadoJuego introducirEnemigo(Enemigo enemigo) {
        ArrayList<Enemigo> enemigos = new ArrayList<>();
        enemigos.add(enemigo);
        return new Jugando(enemigos);
    }

    public EstadoJuego actualizarConVidaDeJugador(int cantVida) {
        return this;
    }

    public void pasarTurno() {};
}
