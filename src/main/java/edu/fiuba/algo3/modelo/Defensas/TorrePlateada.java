package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.miscelanea.Rango;

public class TorrePlateada extends Defensa{

    public TorrePlateada(){
        super();
    }
    public TorrePlateada(Rango rango ,EstadoConstruccion estadoDeConstruccion){
        super(rango, 2);
        this.estadoDeConstruccion = estadoDeConstruccion;
    }
}
