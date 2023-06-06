package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Jugando implements EstadoJuego {

    ArrayList<Enemigo> enemigos;

    public Jugando(ArrayList<Enemigo> enemigos) {
        this.enemigos = enemigos;
    }
    public EstadoJuego introducirEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
        return this;
    }

    public EstadoJuego actualizarConVidaDeJugador(int cantVida) {
        if (cantVida == 0)
            return new Perdido();
        else if (enemigos.stream().filter(enemigo -> enemigo.estaVivo()).count() == 0)
            return new Ganado();
        else
            return this;
    }
    public void pasarTurno(){
        enemigos.forEach(enemigo -> enemigo.avanzar());
    }
}
