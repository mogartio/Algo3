package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

import java.util.ArrayList;

public abstract class Pasarela extends Parcela {

    TipoPasarela tipoPasarela;

    protected ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();
    public Pasarela siguientePasarela;

    public Pasarela(Coordenada coordenada, TipoPasarela tipoPasarela) {
        super(coordenada, new NoDisponible());
        this.tipoPasarela = tipoPasarela;
    }

    public cambiarTipoPasarela(TipoPasarela nuevoTipoPasarela) {
        this.tipoPasarela = nuevoTipoPasarela;
    }

    public abstract Pasarela verSiguiente();

    public abstract Pasarela verSiguiente(int cantidadPasos);

    public abstract void recibir(Enemigo nuevoEnemigo);

    public void actualizarPosicion(Enemigo enemigo, int desplazamientoEnemigo) {
        Pasarela proximaPasarela = this.verSiguiente(desplazamientoEnemigo);
        proximaPasarela.recibir(enemigo);
        enemigos.remove(enemigo);
    }

    public void añadirEnemigo(Enemigo enemigo) {
        enemigos.add(enemigo);
    }

    public void construirDefensa(Defensa defensa){}

    public void dañarEnemigo(int daño){
        this.enemigos.get(0).recibirDaño(daño);
    }

    public boolean tieneEnemigos(){
        return !(enemigos.isEmpty());
    }

    public boolean equals(Pasarela pasarela){
        return pasarela.verificarPosicion(this.coordenada);
    }
}