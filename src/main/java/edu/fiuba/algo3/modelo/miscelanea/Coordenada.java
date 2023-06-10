package edu.fiuba.algo3.modelo.miscelanea;

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

    public boolean distacia(Coordenada coordenada, int rango){
        return coordenada.verificarDistancia(this.coordX, this.coordY, rango);
    }

    public boolean verificarDistancia(int coordX, int coordY, int rango){
        double distacia = Math.sqrt(Math.pow(coordX, 2) + Math.pow(coordY, 2));
        return (distacia == rango);
    }

    public boolean verificarCoordenadas(int coordX, int coordY){
        return (this.coordX == coordX && this.coordY == coordY);
    }
}