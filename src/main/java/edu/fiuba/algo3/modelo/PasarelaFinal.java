package edu.fiuba.algo3.modelo;

public class PasarelaFinal extends Pasarela {

    public PasarelaFinal(Coordenada coordenada, Pasarela pasarelaSiguiente) {
        super(coordenada);
        this.siguientePasarela = null;
    }

    public void recibir(Enemigo nuevoEnemigo){
        nuevoEnemigo.dañarJugador();
    }

    public Pasarela verSiguiente() { return this; }

    public Pasarela verSiguiente(int cantidadPasos) {
        return this;
    }
}
