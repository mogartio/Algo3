package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.miscelanea.Coordenada;
import edu.fiuba.algo3.modelo.Defensas.Defensa;

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