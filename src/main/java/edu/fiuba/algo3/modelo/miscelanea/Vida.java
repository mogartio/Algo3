package edu.fiuba.algo3.modelo.miscelanea;

public class Vida {
    private int puntos;

    public Vida(int puntosIniciales){
        puntos = puntosIniciales;
    }

    public boolean sigueVivo() {
        return puntos > 0;
    }

    public void quitarVida(int puntos){ this.puntos -= puntos; }

    public int obtenerPuntos(){
        return puntos;
    }
}
