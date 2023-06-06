package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public abstract class Pasarela extends Parcela {

    protected ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();

    public Pasarela siguientePasarela;

    public Pasarela(Coordenada coordenada) {
        super(coordenada, new NoDisponible());
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
}