package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.miscelanea.Rango;

public class TorreBlanca extends Defensa{

    public TorreBlanca(){
        super();
    }

    public TorreBlanca(Rango rango,EstadoConstruccion estado){
        super(rango, 1);
        this.estadoDeConstruccion = estado;
    }
}
