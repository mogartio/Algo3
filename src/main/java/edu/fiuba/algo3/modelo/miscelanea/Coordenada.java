package edu.fiuba.algo3.modelo.miscelanea;

import static java.lang.Math.abs;

public class Coordenada {
    private int coordX;
    private int coordY;

    public Coordenada(int x, int y){
        this.coordX = x;
        this.coordY = y;
    }

    public boolean equals(Coordenada coordenada){
        return coordenada.verificarCoordenadas(this.coordX, this.coordY);
    }

    public boolean esBorde(int maximo) {
        return (this.coordX == 0 || this.coordX == maximo || this.coordY == 0 || this.coordY == maximo );
    }

    public boolean verificarDistancia(int coordX, int coordY, int rango){
        return (this.distancia(coordX, coordY) <= rango);
    }

    public boolean estaEnRango(Coordenada otraCoordenada, int rango) {
        return (otraCoordenada.verificarDistancia(this.coordX, this.coordY, rango));
    }

    public int calcularDistancia(Coordenada coordenada){
        return coordenada.distancia(this.coordX, this.coordY);
    }

    public int distancia(int coordX, int coordY){
        int distanciaX = Math.abs(this.coordX - coordX);
        int distanciaY = Math.abs(this.coordY - coordY);

        return distanciaX + distanciaY;
    }

    public boolean verificarCoordenadas(int coordX, int coordY){
        return (this.coordX == coordX && this.coordY == coordY);
    }

    public String representacionString(){
        return ("( " + Integer.toString(this.coordX) + ", " + Integer.toString(this.coordY) + " )");
    }

    public int getAbscisa(){
        return this.coordX;
    }

    public int getOrdenada(){
        return this.coordY;
    }
}