package edu.fiuba.algo3.modelo;

public class Tierra extends Parcela {
    private Defensa defensa;

    public Tierra(Coordenada coordenada){
        super(coordenada, new Disponible());
    }

    public void construirDefensa(Defensa defensa){
        this.defensa = defensa;
        setConstruible(new NoDisponible());
    }

    public boolean ocupada(){
        return !(construible.puedeConstruir());
    }
}