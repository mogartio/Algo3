package edu.fiuba.algo3.modelo;

import java.util.ArrayList;

public class Pasarela extends Parcela {
    private ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();

    public Pasarela(Coordenada coordenada){
        super(coordenada, new NoDisponible());
    }

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