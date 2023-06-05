package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Pasarela extends Parcela {
    private ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();

    public Pasarela siguientePasarela;

    public Pasarela(Coordenada coordenada, Pasarela pasarelaSiguiente){
        super(coordenada, new NoDisponible());
        this.siguientePasarela = pasarelaSiguiente;
    }

    public Pasarela verSiguiente() { return siguientePasarela; }

    public void añadirEnemigo(Enemigo nuevoEnemigo){
        this.enemigos.add(nuevoEnemigo);
    }

    public void construirDefensa(Defensa defensa){}

    public void dañarEnemigo(int daño){
        this.enemigos.get(0).recibirDaño(daño);
    }

    public boolean tieneEnemigos(){
        return !(enemigos.isEmpty());
    }
}