package edu.fiuba.algo3.modelo;

public class Vida {
    private int puntos;

    public Vida(int puntosIniciales){
        puntos = puntosIniciales;
    }

    public void quitarVida(int puntos){
        this.puntos -= puntos;
    }

    public int obtenerPuntos(){
        return puntos;
    }
}
