package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;
import edu.fiuba.algo3.modelo.ObserverPropio.Emisor;
import edu.fiuba.algo3.modelo.lectorJSON.Mapa;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Jugando implements EstadoJuego {

    ArrayList<Enemigo> enemigos;
    ArrayList<Defensa> defensas;
    Mapa mapa;
    public Jugando(){
        this.mapa = new Mapa();
        this.enemigos = new ArrayList<>();
        this.defensas = new ArrayList<>();
    }

    public Jugando(Mapa mapa) {
        this.mapa = mapa;
        this.enemigos = new ArrayList<>();
        this.defensas = new ArrayList<>();
    }

    public EstadoJuego introducirEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
        return this;
    }

    public boolean finalizado() {
        return false;
    }

    public EstadoJuego introducirDefensa(Defensa nuevaDefensa) {
        defensas.add(nuevaDefensa);
        return this;
    }

    private EstadoJuego actualizarSegunEstadoDeJugador(boolean jugadorVivo) {
        if (!jugadorVivo)
            return new Perdido();
        else if (enemigos.isEmpty())
            return new Ganado();
        else
            return this;
    }

    public EstadoJuego jugarTurno(boolean jugadorVivo, int numeroTurno){
        //emisor.notificarSubscriptores("log", "Se juega el turno N°: " + Integer.toString(numeroTurno));

        if (!enemigos.isEmpty()){

            enemigos.forEach(enemigo -> {
                try {
                    enemigo.avanzar(this.mapa);
                } catch (PasarelaInexistente ignored) {
                }
            });
        }

        this.limpiezaEnemigosMuertos();
        mapa.agregarEnemigosDelTurno(this.enemigos);

        if (!defensas.isEmpty()){

            defensas.forEach(defensa -> {
                defensa.atacar(enemigos);
                defensa.pasarTurno();
            });
        }
        this.limpiezaEnemigosMuertos();

        return actualizarSegunEstadoDeJugador(jugadorVivo);
    }

    private void limpiezaEnemigosMuertos(){
        this.enemigos = this.enemigos.stream().filter(Enemigo::estaVivo).collect(Collectors.toCollection(ArrayList::new));;
    }
}
