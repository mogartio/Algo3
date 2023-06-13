package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Observer.Observable;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;
import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.miscelanea.Tienda;

import java.util.ArrayList;

public class Juego extends Observable {

    Jugador jugador;
    EstadoJuego estadoJuego;
    Tienda tiendaDefensas = new Tienda();

    public Juego() {
        this.jugador = Jugador.getInstance();
        estadoJuego = new Jugando();
    }
    public Juego(Mapa mapa) {
        this.jugador = Jugador.getInstance();
        estadoJuego = new Jugando(mapa);
    }

    public void comprarDefensa(String unaDefensa, Coordenada coordenada) {
       Defensa nuevaDefensa = tiendaDefensas.vendeme(unaDefensa);
       nuevaDefensa.asignarPosicion(coordenada);
       estadoJuego.introducirDefensa(nuevaDefensa);

       this.emisor.notificarSubscriptores("log", "Se agrega al juego una nueva defensa: " + unaDefensa + " en " + coordenada.representacionString());
    }

    public void nuevoEnemigo(Enemigo nuevoEnemigo) {
        estadoJuego = this.estadoJuego.introducirEnemigo(nuevoEnemigo);
        this.emisor.notificarSubscriptores("log", "Se agrega a la partida un nuevo enemigo " + nuevoEnemigo.representacionString());
    }

    public boolean finalizado() {
        return this.estadoJuego.finalizado();
    }

    public void jugarTurno(int numeroTurno) {
        this.estadoJuego = estadoJuego.jugarTurno(jugador.estaVivo(), numeroTurno, this.emisor);
    }
}
