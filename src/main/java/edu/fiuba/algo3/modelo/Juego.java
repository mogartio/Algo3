package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Juego {

    Jugador jugador;

    EstadoJuego estadoJuego;
    public Juego(Jugador jugador) {
        this.jugador = jugador;
        estadoJuego = new NoComenzado();
    }
    public Juego(Jugador jugador, ArrayList<Enemigo> enemigos) {
        this.jugador = jugador;
        estadoJuego = new Jugando(enemigos);
    }
    public void nuevoEnemigo(Enemigo nuevoEnemigo) {
        estadoJuego = this.estadoJuego.introducirEnemigo(nuevoEnemigo);
    }

    public void actualizarEstado() {
        estadoJuego = estadoJuego.actualizarConVidaDeJugador(jugador.obtenerVida());
    }

    public EstadoJuego estado() {
        actualizarEstado();
        return estadoJuego;
    }


}
