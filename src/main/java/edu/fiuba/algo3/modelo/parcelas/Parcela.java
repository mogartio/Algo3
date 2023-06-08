package edu.fiuba.algo3.modelo.parcelas;


import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.Defensas.Defensa;

public abstract class Parcela {
    protected Coordenada coordenada;
    protected Construible construible;

    public Parcela(Coordenada coordenada, Construible construible){
        this.coordenada = coordenada;
        this.construible = construible;
    }

    public abstract void construirDefensa(Defensa defensa);

    public boolean equals(Coordenada coordenada){
        return (this.coordenada.equals(coordenada));
    }

    protected void setConstruible(Construible nuevoConstruible){
        this.construible = nuevoConstruible;
    }
}