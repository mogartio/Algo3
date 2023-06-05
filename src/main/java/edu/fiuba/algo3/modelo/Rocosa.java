package edu.fiuba.algo3.modelo;

public class Rocosa extends Parcela {
    public Rocosa(Coordenada coordenada){
        super(coordenada, new NoDisponible());
    }
    public void construirDefensa(Defensa defensa){}

    public boolean ocupada(){
        return false;
    }
}