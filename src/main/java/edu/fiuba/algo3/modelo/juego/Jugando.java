package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Trampa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Excepciones.PasarelaInexistente;

import java.util.ArrayList;
import java.util.Observable;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Jugando implements EstadoJuego {

    ArrayList<Enemigo> enemigos;
    LinkedList<Defensa> defensas;
    Mapa mapa;
    public Jugando(){
        this.mapa = new Mapa();
        this.enemigos = new ArrayList<>();
        this.defensas = new LinkedList<>();
    }

    public Jugando(Mapa mapa) {
        this.mapa = mapa;
        this.enemigos = new ArrayList<>();
        this.defensas = new LinkedList<>();
    }

    public EstadoJuego introducirEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
        return this;
    }

    @Override
    public void notificar() {
        enemigos.forEach(Observable::notifyObservers);
        defensas.forEach(Observable::notifyObservers);
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
        else if (enemigos.isEmpty() && mapa.noHayMasEnemigos())
            return new Ganado();
        else
            return this;
    }

    public EstadoJuego jugarTurno(boolean jugadorVivo, int numeroTurno){
        if (!defensas.isEmpty()){
           defensas.forEach(defensa -> {
                defensa.atacar(enemigos);
                defensa.pasarTurno();
            });
        }


        if (!enemigos.isEmpty()){

            enemigos.forEach(enemigo -> {
                try {
                    enemigo.avanzar(this.mapa);
                } catch (PasarelaInexistente ignored) {
                }
            });
        }


        mapa.agregarEnemigosDelTurno(this.enemigos);


        this.limpiezaEnemigosMuertos();

        return actualizarSegunEstadoDeJugador(jugadorVivo);
    }

    private void limpiezaEnemigosMuertos(){
        this.enemigos = this.enemigos.stream().filter(Enemigo::estaVivo).collect(Collectors.toCollection(ArrayList::new));;
    }

    public void destruirDefensaMasAntigua(){
        if ( !defensas.isEmpty() ) {
            defensas.removeFirst();
        }
    }
    @Override
    public String versionString() { return "Jugando"; }

    @Override
    public void quitarTrampa(Defensa unaTrampa) {
        defensas.remove(unaTrampa);
    }
}
