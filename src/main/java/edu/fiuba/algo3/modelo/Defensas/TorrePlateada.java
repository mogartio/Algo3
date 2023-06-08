package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.miscelanea.Rango;

public class TorrePlateada extends Defensa{

    public TorrePlateada(){
        super();
    }


    /*
        public TorrePlateada(Rango rango , EstadoConstruccion estado){
            super(rango, 2);

        }*/
    public TorrePlateada(EstadoConstruccion estadoDeConstruccion){
        this.estadoDeConstruccion = estadoDeConstruccion;
    }
}
