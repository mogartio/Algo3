package edu.fiuba.algo3.modelo.miscelanea;

public class Coordenada {
    private int coordX;
    private int coordY;

    public Coordenada(int x, int y){
        this.coordX = x;
        this.coordY = y;
    }

    public boolean equals(Coordenada coordenada){
        return (this.coordY == coordenada.devolverY() && this.coordX == coordenada.devolverX());
    }

    public int devolverY(){
        return this.coordY;
    }

    public int devolverX(){
        return this.coordX;
    }
}